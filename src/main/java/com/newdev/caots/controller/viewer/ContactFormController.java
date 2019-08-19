package com.newdev.caots.controller.viewer;

import com.newdev.caots.common.Token;
import com.newdev.caots.entities.ContactForm;
import com.newdev.caots.entities.Record;
import com.newdev.caots.service.ContactFormService;
import com.newdev.caots.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/v1/public/contact-form")
public class ContactFormController {

    @Autowired
    private ContactFormService contactFormService;

    @Autowired
    private RecordService recordService;


    @GetMapping(value = "/find-by-id")
    public ResponseEntity<ContactForm> findContactFormById(
            @RequestParam("id") int id,
            @RequestHeader("adminbksoftwarevn") String header
    ) {
        if (header.equals(Token.tokenHeader)) {

            return new ResponseEntity<>(contactFormService.findById(id), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    //=============================================================================
    @PostMapping
    public ResponseEntity<ContactForm> createContactForm(
            @RequestBody ContactForm contactForm,
            @RequestHeader("adminbksoftwarevn") String header
    ) {
        if (header.equals(Token.tokenHeader)) {
            contactForm.setStatus(true);
            boolean result = contactFormService.saveContactForm(contactForm);
            System.out.println(result);
            Record record = recordService.findByName("contact_form");
            if (result) {
                record.setNumber(record.getNumber() + 1);
                recordService.saveRecord(record);
                return new ResponseEntity<>(contactForm, HttpStatus.OK);
            }
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }


}