package com.newdev.caots.service_impl.admin;

import com.newdev.caots.entities.admin.AboutMe;
import com.newdev.caots.repository.admin.AboutMeRepository;
import com.newdev.caots.service.admin.AboutMeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class AboutMeService_Impl implements AboutMeService {

    private final static Logger LOGGER = Logger.getLogger(AboutMeService_Impl.class.getName());

    @Autowired
    private AboutMeRepository aboutMeRepository;

    @Override
    public List<AboutMe> findAllAboutMe() {
        try {
            return aboutMeRepository.findByStatus(true);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "find-all-about-me-error : {0}", ex.getCause());
        }
        return null;
    }

    @Override
    public AboutMe findById(int aboutMeId) {
        try {
            AboutMe aboutMe = aboutMeRepository.findById(aboutMeId);
            if (aboutMe.isStatus()) {
                return aboutMe;
            }
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "find-by-about-me-by-id-error : {0}", ex.getCause());
        }
        return null;
    }

    @Override
    public boolean save(AboutMe aboutMe) {
        try {
            aboutMeRepository.save(aboutMe);
            return true;
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "save-about-me-error : {0}", ex.getCause());
        }
        return false;
    }

    @Override
    public boolean delete(int aboutMeId) {
        try {
            AboutMe aboutMe = findById(aboutMeId);
            aboutMe.setStatus(false);
            aboutMeRepository.save(aboutMe);
            return true;
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "delete-about-me-error : {0}", ex.getCause());
        }
        return false;
    }
}
