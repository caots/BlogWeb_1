package com.newdev.caots.service_impl.admin;

import com.newdev.caots.entities.admin.AppRole;
import com.newdev.caots.repository.admin.AppRoleRepository;
import com.newdev.caots.service.admin.AppRoleService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class AppRoleService_Impl implements AppRoleService {

    private final static Logger LOGGER = Logger.getLogger(AppRoleService_Impl.class.getName());

    private final
    AppRoleRepository appRoleRepository;

    public AppRoleService_Impl(AppRoleRepository appRoleRepository) {
        this.appRoleRepository = appRoleRepository;
    }

    @Override
    public List<AppRole> findAll() {
        try {
            return appRoleRepository.findAll();
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "find-all-role-error : {0}", ex.getCause());
        }
        return new ArrayList<>();
    }

    @Override
    public AppRole findByName(String name) {
        try {
            return appRoleRepository.findByName(name);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "find-role-error : {0}", ex.getCause());
        }
        return null;
    }
}
