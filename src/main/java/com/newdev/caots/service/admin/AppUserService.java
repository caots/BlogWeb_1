package com.newdev.caots.service.admin;

import com.newdev.caots.entities.admin.AppUser;
import com.newdev.caots.entities.json_playload.RegisterForm;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AppUserService {
    AppUser findByEmail(String email);

    AppUser findByEmailAndPassword(String email, String password);

    AppUser findById(int id);

    List<AppUser> findAllUserByName(String name);

    List<AppUser> findAll();

    boolean saveAppUser(AppUser appUser);

    boolean saveAppUser(RegisterForm registerForm);

    boolean deleteAppUser(AppUser appUser);

    List<AppUser> findAllPage(Pageable pageable);

}
