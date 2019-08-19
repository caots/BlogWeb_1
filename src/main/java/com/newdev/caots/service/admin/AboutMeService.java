package com.newdev.caots.service.admin;

import com.newdev.caots.entities.admin.AboutMe;

import java.util.List;

public interface AboutMeService {

    List<AboutMe> findAllAboutMe();

    AboutMe findById(int aboutMeId);

    boolean save(AboutMe aboutMe);

    boolean delete(int aboutMeId);
}
