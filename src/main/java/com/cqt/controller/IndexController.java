package com.cqt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping("/")
    public String index() {
        // Return the name of the Thymeleaf template (without .html)
        return "index";
    }
}