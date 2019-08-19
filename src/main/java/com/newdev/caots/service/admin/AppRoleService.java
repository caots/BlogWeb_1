package com.newdev.caots.service.admin;

import com.newdev.caots.entities.admin.AppRole;

import java.util.List;

public interface AppRoleService {

    List<AppRole> findAll();

    AppRole findByName(String name);

}