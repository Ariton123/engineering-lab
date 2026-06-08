package com.sportifyhub.controller;

import com.sportifyhub.model.User;
import com.sportifyhub.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RegistrationController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@RequestParam("username") String username,
                               @RequestParam("email") String email,
                               @RequestParam("password") String password,
                               @RequestParam("name") String name,
                               @RequestParam("age") String age,
                               @RequestParam("height") String height,
                               @RequestParam("weight") String weight,
                               @RequestParam("gender") String gender,
                               @RequestParam("goal") String goal,
                               @RequestParam("favoriteSport") String favoriteSport,
                               Model model) {
        // Encrypt the password before saving
        String encodedPassword = passwordEncoder.encode(password);
        User user = new User();
        user.setUsername(username);
        user.setPassword(encodedPassword);
        user.setEmail(email);
        user.setName(name);
        user.setAge(age);
        user.setHeight(height);
        user.setWeight(weight);
        user.setGender(gender);
        user.setGoal(goal);
        user.setFavoriteSport(favoriteSport);
        userRepository.save(user);

        return "redirect:/login";
    }
}
