package com.sportifyhub.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.sportifyhub.model.User;
import com.sportifyhub.service.UserService;

@Controller
public class HomeController {

    private final UserService userService;

    public HomeController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/home")
    public String home(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        // Fetch the user from the database using the username
        User user = userService.findByUsername(username);

        // Add the user's goal to the model
        model.addAttribute("username", username);
        model.addAttribute("goal", user != null ? user.getGoal() : "Unknown");
        return "home";
    }
}
