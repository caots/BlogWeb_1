package com.newdev.caots.repository.admin;

import com.newdev.caots.entities.admin.AppUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Integer> {

    @Query("SELECT ap FROM AppUser ap WHERE ap.status = true and ap.id > 1and ap.fullName LIKE CONCAT('%',:name_user,'%') order by id desc ")
    List<AppUser> findAllUserByName(@Param("name_user") String name);

    AppUser findByEmail(String email);

    AppUser findById(int id);

    @Query("select u from AppUser u where u.email= :email and u.password= :password and u.status=true")
    AppUser findAppUserLogin(@Param("email") String email, @Param("password") String password);

    List<AppUser> findAll();

    List<AppUser> findByStatus(boolean status);

    @Query("select u from AppUser u where u.status=true and u.id >1 ")
    Page<AppUser> findAllPage(Pageable pageable);

    Page<AppUser> findByFullName(String name, Pageable pageable);

}