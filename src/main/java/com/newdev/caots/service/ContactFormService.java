package com.newdev.caots.service;

import com.newdev.caots.entities.ContactForm;

import java.util.List;

public interface ContactFormService {

    List<ContactForm> findAllContactForm();

    ContactForm findById(int id);

    boolean saveContactForm(ContactForm contactForm);

    boolean deleteContactForm(ContactForm contactForm);
}
