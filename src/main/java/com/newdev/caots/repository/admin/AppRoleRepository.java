package com.newdev.caots.repository.admin;

import com.newdev.caots.entities.admin.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppRoleRepository extends JpaRepository<AppRole, Integer> {

    List<AppRole> findAll();

    AppRole findByName(String name);


}