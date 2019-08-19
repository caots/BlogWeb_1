package com.newdev.caots.controller.admin.user;

import com.newdev.caots.entities.Record;
import com.newdev.caots.entities.admin.AppUser;
import com.newdev.caots.service.RecordService;
import com.newdev.caots.service.admin.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("api/v1/admin/user")
@RolesAllowed("ROLE_ADMIN")
public class AdminUserController {

    @Autowired
    private AppUserService appUserService;

    @Autowired
    private RecordService recordService;


    @GetMapping("/page")
    public ResponseEntity<List<AppUser>> findAllUser(
            @RequestParam(name = "page", required = false, defaultValue = "1") int page,
            @RequestParam(name = "size", required = false, defaultValue = "15") int size,
            HttpServletResponse response
    ) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        if (page < 1) page = 1;
        if (size < 0) size = 10;
        Pageable pageable = PageRequest.of(page - 1, size);
        List<AppUser> users = appUserService.findAllPage(pageable);
        if (users != null) return new ResponseEntity<>(users, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @GetMapping(value = "/size")
    public ResponseEntity<Double> pageNumberUser(
            HttpServletResponse response
    ) {

        Record record = recordService.findByName("user");
        double result = Math.ceil((double) record.getNumber() / 15);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/name/all")
    public ResponseEntity<List<AppUser>> findAllUser(
            HttpServletResponse response,
            @RequestParam("name") String name
    ) {
        response.setHeader("Access-Control-Allow-Origin", "*");

        List<AppUser> users = appUserService.findAllUserByName(name);
        if (users != null) return new ResponseEntity<>(users, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @PutMapping("/delete")
    public ResponseEntity<Object> deleteUser(@RequestParam("id") int id,
                                             HttpServletResponse response
    ) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        AppUser appUser = appUserService.findById(id);
        if (appUser == null) return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        Record record = recordService.findByName("user");
        record.setNumber(record.getNumber() - 1);
        boolean result = appUserService.deleteAppUser(appUser);
        if (result) {
            recordService.saveRecord(record);
            return new ResponseEntity<>("delete user fail", HttpStatus.OK);
        }
        return new ResponseEntity<>("delete user fail", HttpStatus.BAD_REQUEST);
    }
}
