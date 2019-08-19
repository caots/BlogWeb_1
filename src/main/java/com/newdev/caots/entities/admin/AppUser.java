package com.newdev.caots.entities.admin;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;

@Entity
@Data
@Table(name = "user")

public class AppUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String email;

    private String password;

    @Column(name = "full_name")
    private String fullName;

    private String image;

    private boolean status;

    @Column(name = "init_date")
    private LocalDate initDate;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "user_has_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<AppRole> appRoles;

    public Collection<? extends GrantedAuthority> grantedAuthorities() {
        List<GrantedAuthority> list = new ArrayList<>();
        appRoles.forEach(role -> list.add(new SimpleGrantedAuthority(role.getName())));
        //list.add(new SimpleGrantedAuthority("ROLE_USER"));
        return list;
    }

}