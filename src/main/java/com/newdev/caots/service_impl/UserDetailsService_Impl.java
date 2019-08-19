package com.newdev.caots.service_impl;


import com.newdev.caots.entities.admin.AppRole;
import com.newdev.caots.entities.admin.AppUser;
import com.newdev.caots.service_impl.admin.AppUserService_Impl;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserDetailsService_Impl implements UserDetailsService {

    private final AppUserService_Impl appUserService;

    public UserDetailsService_Impl(AppUserService_Impl appUserService) {
        this.appUserService = appUserService;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        AppUser appUser = appUserService.findByEmail(s);
        if (appUser == null) throw new UsernameNotFoundException(s);

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        List<AppRole> roles = appUser.getAppRoles();
        for (AppRole role : roles) {
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return new User(appUser.getEmail(), appUser.getPassword(), grantedAuthorities);
    }
}