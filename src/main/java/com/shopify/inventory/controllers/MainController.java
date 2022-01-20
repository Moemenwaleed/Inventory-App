package com.shopify.inventory.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * MainController is responsible to create the permitted requests to the basic Interface.
 *
 * It consists of two mappings,
 * "localhost:8080" is going to access the home.html inside templates.
 * "localhost:8080/js/{file}" is going to allow access to any Javascript file inside templates/js.
 *
 */

@Controller
public class MainController {
    @Value("${spring.application.name}")
    String appName;

    @GetMapping("/")
    public String homePage(Model model) {
        model.addAttribute("appName", appName);
        return "home";
    }

    //Allow accessing javascript files..
    @GetMapping("/js/{file}")
    public String viewJavascript(@PathVariable String file) {
        return "js/"+file;
    }
}
