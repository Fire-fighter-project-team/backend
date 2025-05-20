package com.firetrack.project.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {
    @GetMapping("/test")
    public String redirectToTest() {
        return "redirect:/test.html";
    }
}
