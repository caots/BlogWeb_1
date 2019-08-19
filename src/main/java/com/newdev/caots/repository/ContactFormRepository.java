package com.newdev.caots.repository;


import com.newdev.caots.entities.ContactForm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ContactFormRepository extends JpaRepository<ContactForm, Integer> {

    @Query("select cf from ContactForm cf where cf.status = true and  b.menu.id= :id")
    List<ContactForm> findByStatus(boolean status);

    ContactForm findById(int id);


}
