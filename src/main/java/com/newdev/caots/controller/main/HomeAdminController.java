package com.newdev.caots.controller.main;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class HomeAdminController {

    private String token;

    public String getToken(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            System.out.println(cookie.getName());
            if (cookie.getName().equals("token")) {
                token = cookie.getValue();
            }
        }

        return token;
    }

    @RequestMapping(value = {"/", "/login", "/logout"}, method = RequestMethod.GET)
    public String pageLogin(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (int i = 0; i < cookies.length; i++) {
                cookies[i].setMaxAge(0);
            }
        }
        return "login";
    }

    @RequestMapping(value = {"/home"}, method = RequestMethod.GET)
    public String homePage(HttpServletRequest request) {

        String token = getToken(request);

        if (token == null) {
            return "login";
        }
        return "home";
    }

    @RequestMapping(value = {"/user"}, method = RequestMethod.GET)
    public String userPage(HttpServletRequest request) {

        String username = getToken(request);

        if (username == null) {
            return "login";
        }
        return "user";
    }

    @RequestMapping(value = {"/about-me"}, method = RequestMethod.GET)
    public String aboutMePage(HttpServletRequest request) {

        String username = getToken(request);

        if (username == null) {
            return "login";
        }
        return "about-me";
    }

    @RequestMapping(value = {"/profile"}, method = RequestMethod.GET)
    public String profilePage(HttpServletRequest request) {

        String username = getToken(request);

        if (username == null) {
            return "login";
        }
        return "profile-admin";
    }

    @RequestMapping(value = {"/category"}, method = RequestMethod.GET)
    public String topicPage(HttpServletRequest request) {

        String username = getToken(request);

        if (username == null) {
            return "login";
        }
        return "category";
    }

    @RequestMapping(value = {"/news"}, method = RequestMethod.GET)
    public String newsPage(HttpServletRequest request) {

        String username = getToken(request);
        if (username == null) {
            return "login";
        }
        return "news";
    }

    @GetMapping("/form-send-email")
    public String sendMailFormPage(
            @RequestParam("id") int id,
            HttpServletRequest request
    ) {
        String username = getToken(request);

        if (username == null) {
            return "login";
        }
        return "form-send-email";
    }

    @GetMapping("/content-form-contact")
    public String contentFormMailFormPage(
            @RequestParam("id") int id,
            HttpServletRequest request
    ) {
        String username = getToken(request);

        if (username == null) {
            return "login";
        }
        return "content-form-contact";
    }

    @RequestMapping(value = {"/form-contact"}, method = RequestMethod.GET)
    public String formContactPage(HttpServletRequest request) {

        String username = getToken(request);

        if (username == null) {
            return "login";
        }
        return "form-contact";
    }

}
