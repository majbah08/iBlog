package com.squarehealth.iBlog.controllers;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.squarehealth.iBlog.models.AppUsers;

@Controller
@RequestMapping("/")
public class AuthController {
	
    @GetMapping("/login")
    public String getLoginFrom(Principal principal) {

        if (principal != null) {
            return "redirect:/home";
        }
        return "/login";
    }

    @GetMapping("/registration")
    public String getRegistartionFrom(Model model) {

        model.addAttribute("user", new AppUsers());

        return "/registration";
    }
    }
