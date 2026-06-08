package com.sportifyhub.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ScheduleController {

    @GetMapping("/schedule/{sport}")
    public String showScheduleForm(@PathVariable String sport, Model model) {
        // Add the selected sport to the model
        model.addAttribute("selectedSport", sport);

        return "schedule";
    }
}
