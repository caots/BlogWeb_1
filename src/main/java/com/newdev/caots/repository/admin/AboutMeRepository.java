package com.newdev.caots.repository.admin;

import com.newdev.caots.entities.admin.AboutMe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AboutMeRepository extends JpaRepository<AboutMe, Integer> {

    List<AboutMe> findByStatus(boolean status);

    AboutMe findById(int id);
}
