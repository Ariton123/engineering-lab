package com.sportifyhub.controller;

import com.sportifyhub.model.User;
import com.sportifyhub.service.RecommendationService;
import com.sportifyhub.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RecommendationService recommendationService;

    @GetMapping
    public String getAllUsers(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "users";
    }

    @GetMapping("/profile")
    public String getUserProfile(Model model) {
        Long loggedInUserId = 1L;
        User user = userService.getUserById(loggedInUserId);
        model.addAttribute("user", user);
        return "profile";
    }

    @GetMapping("/{id}")
    public String getUserById(@PathVariable Long id, Model model) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "user";
    }

    @PostMapping
    public String createUser(@ModelAttribute User user) {
        userService.saveUser(user);
        return "redirect:/api/users";
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return "redirect:/api/users";
    }

    @GetMapping("/recommendations/{id}")
    public String getRecommendations(@PathVariable Long id, Model model) {
        User user = userService.getUserById(id);
        List<String> recommendations = recommendationService.generateRecommendations(user);
        model.addAttribute("recommendations", recommendations);
        return "recommendations";
    }

    @PostMapping("/rate")
    public String submitRating(@RequestParam int rating) {
        // Save the rating to the database or process it as needed
        return "redirect:/recommendations?success";
    }


}
