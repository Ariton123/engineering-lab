package com.sportifyhub.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class RecommendationsController {

    @PostMapping("/recommendations")
    public String getRecommendations(@RequestParam("freeTime") String freeTime,
                                     @RequestParam("goal") String goal,
                                     Model model) {
        // Logic to generate recommendations based on freeTime and goal
        List<String> individualSports = new ArrayList<>();
        List<String> teamSports = new ArrayList<>();
        List<String> adventureSports = new ArrayList<>();
        List<String> racquetSports = new ArrayList<>();

        // Sample recommendations
        individualSports.add("Running");
        individualSports.add("Cycling");
        individualSports.add("Yoga");

        teamSports.add("Football");
        teamSports.add("Basketball");
        teamSports.add("Volleyball");

        adventureSports.add("Rock Climbing");
        adventureSports.add("Hiking");
        adventureSports.add("Surfing");

        racquetSports.add("Tennis");
        racquetSports.add("Badminton");
        racquetSports.add("Squash");

        model.addAttribute("freeTime", freeTime);
        model.addAttribute("goal", goal);
        model.addAttribute("teamSports", teamSports);
        model.addAttribute("individualSports", individualSports);
        model.addAttribute("combatSports", teamSports);
        model.addAttribute("adventureSports", adventureSports);
        model.addAttribute("racquetSports", racquetSports);
        model.addAttribute("selectedTime", freeTime);

        return "recommendations";
    }
}
