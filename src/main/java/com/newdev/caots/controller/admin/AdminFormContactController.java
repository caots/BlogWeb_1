package com.newdev.caots.controller.admin;

import com.newdev.caots.entities.ContactForm;
import com.newdev.caots.entities.Record;
import com.newdev.caots.service.ContactFormService;
import com.newdev.caots.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("api/v1/admin/contact-form")
@RolesAllowed("ROLE_ADMIN")
public class AdminFormContactController {

    @Autowired
    private RecordService recordService;

    @Autowired
    private ContactFormService contactFormService;

    @GetMapping("/all")
    public ResponseEntity<List<ContactForm>> findAllContactForm(
            HttpServletResponse response
    ) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        List<ContactForm> contactForms = contactFormService.findAllContactForm();
        if (contactForms != null) return new ResponseEntity<>(contactForms, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/find-by-id")
    public ResponseEntity<Object> findContactFormById(
            @RequestParam("id") int id,
            HttpServletResponse response
    ) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        ContactForm contactForm = contactFormService.findById(id);
        if (contactForm != null) return new ResponseEntity<>(contactForm, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping
    public ResponseEntity<Object> checkedContactForm(@RequestBody ContactForm contactForm,
                                                     HttpServletResponse response
    ) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        System.out.println(contactForm);
        contactForm.setChecked(true);
        boolean result = contactFormService.saveContactForm(contactForm);
        if (result) {
            return new ResponseEntity<>(contactForm, HttpStatus.OK);
        }
        return new ResponseEntity<>("checked contact form fail", HttpStatus.BAD_REQUEST);
    }


    @PutMapping("/delete")
    public ResponseEntity<Object> deleteContactForm(@RequestParam("id") int id,
                                                    HttpServletResponse response
    ) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        ContactForm contactForm = contactFormService.findById(id);
        if (contactForm == null) return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        Record record = recordService.findByName("contact_form");
        record.setNumber(record.getNumber() - 1);
        boolean result = contactFormService.deleteContactForm(contactForm);
        if (result) {
            recordService.saveRecord(record);
            return new ResponseEntity<>("delete contact form success", HttpStatus.OK);
        }
        return new ResponseEntity<>("delete contact form fail", HttpStatus.BAD_REQUEST);
    }
}
