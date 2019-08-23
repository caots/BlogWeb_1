package com.newdev.caots.service_impl;

import com.newdev.caots.entities.ContactForm;
import com.newdev.caots.repository.ContactFormRepository;
import com.newdev.caots.service.ContactFormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactFormService_Impl implements ContactFormService {

    @Autowired
    private ContactFormRepository contactFormRepository;

    @Override
    public List<ContactForm> findAllContactForm() {
        return contactFormRepository.findByStatus();
    }

    @Override
    public ContactForm findById(int id) {
        return contactFormRepository.findById(id);
    }

    @Override
    public boolean saveContactForm(ContactForm contactForm) {
        contactFormRepository.save(contactForm);
        return true;
    }

    @Override
    public boolean deleteContactForm(ContactForm contactForm) {
        contactForm.setStatus(false);
        contactFormRepository.save(contactForm);
        return false;
    }
}
