package com.cqt.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CustomErrorController implements ErrorController {
    @RequestMapping("/error")
    public String handleError(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("jakarta.servlet.error.status_code");
        if (statusCode == 403) {
            return "error/403";  // Map to 404.html
        } else
        if (statusCode == 404) {
            return "error/404";  // Map to 404.html
        } else if (statusCode == 500) {
            return "error/500";  // Map to 500.html
        }

        return "error/error";  // General error page
    }

    public String getErrorPath() {
        return "/error";
    }
}