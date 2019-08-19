package com.newdev.caots.controller.viewer.user;

import com.newdev.caots.entities.Record;
import com.newdev.caots.entities.admin.AppUser;
import com.newdev.caots.entities.json_playload.LoginForm;
import com.newdev.caots.entities.json_playload.RegisterForm;
import com.newdev.caots.service.RecordService;
import com.newdev.caots.service.admin.AppUserService;
import com.newdev.caots.service_impl.JWTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;

@RestController
@RequestMapping("/api/v1/public/user")
public class UserSecurityController {

    @Autowired
    private AppUserService appUserService;

    @Autowired
    private JWTService jwtService;

    @Autowired
    private RecordService recordService;


    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody LoginForm loginForm,
                                        HttpServletResponse response
    ) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        AppUser appUser = appUserService.findByEmailAndPassword(loginForm.getEmail(), loginForm.getPassword());
        if (appUser == null)
            return new ResponseEntity<>("username or password is not correct", HttpStatus.OK);
        else {
            String token = jwtService.generateToken(appUser.getEmail());
            return new ResponseEntity<>(token, HttpStatus.OK);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<Object> register(@RequestBody RegisterForm registerForm,
                                           HttpServletResponse response
    ) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        if (appUserService.findByEmail(registerForm.getEmail()) != null) {

            return new ResponseEntity<>("Email has been used", HttpStatus.BAD_REQUEST);
        }
        registerForm.setInitDate(LocalDate.now());
        if (appUserService.saveAppUser(registerForm)) {
            Record record = recordService.findByName("user");
            record.setNumber(record.getNumber() + 1);
            AppUser appUser = appUserService.findByEmail(registerForm.getEmail());
            recordService.saveRecord(record);
            return new ResponseEntity<>(appUser, HttpStatus.OK);
        }
        return new ResponseEntity<>("register fail", HttpStatus.BAD_REQUEST);
    }

}