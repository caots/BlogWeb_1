package com.newdev.caots.controller.main;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ChangeAdminController {

    private String getToken(HttpServletRequest request) {
        HomeAdminController homeAdminController = new HomeAdminController();
        return homeAdminController.getToken(request);
    }

    @GetMapping("/create-category")
    public String createCategoryPage(HttpServletRequest request) {


        String username = getToken(request);

        if (username == null) {
            return "login";
        }
        return "create-category";
    }


    @GetMapping("/update-category")
    public String updateCategoryPage(
            @RequestParam("id") int id,
            HttpServletRequest request
    ) {
        String username = getToken(request);

        if (username == null) {
            return "login";
        }
        return "update-category";
    }

    @GetMapping("/create-news")
    public String createNewsPage(HttpServletRequest request) {


        String username = getToken(request);

        if (username == null) {
            return "login";
        }
        return "create-news";
    }


    @GetMapping("/update-news")
    public String updateNewsPage(
            @RequestParam("id") int id,
            HttpServletRequest request
    ) {
        String username = getToken(request);

        if (username == null) {
            return "login";
        }
        return "update-news";
    }

    @GetMapping("/create-about-me")
    public String createAboutMePage(HttpServletRequest request) {

        String username = getToken(request);

        if (username == null) {
            return "login";
        }
        return "create-about-me";
    }


    @GetMapping("/update-about-me")
    public String updateAboutMePage(
            @RequestParam("id") int id,
            HttpServletRequest request
    ) {
        String username = getToken(request);

        if (username == null) {
            return "login";
        }
        return "update-about-me";
    }
}
