package com.sportifyhub.service;

import com.sportifyhub.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RecommendationService {

    public List<String> generateRecommendations(User user) {
        List<String> recommendations = new ArrayList<>();
        String goal = user.getGoal();
        String freeTime = user.getFreeTime();
        String favoriteSport = user.getFavoriteSport();

        if (freeTime.equals("less than an hour")) {
            recommendations.add("Quick jog");
            recommendations.add("Stretching exercises");
        } else if (freeTime.equals("1 to 2 hours")) {
            recommendations.add("Full body workout");
            recommendations.add("Team sports practice");
        } else if (freeTime.equals("more than 2 hours")) {
            recommendations.add("Hiking");
            recommendations.add("Adventure sports");
        }

        if (goal.equals("fitness")) {
            recommendations.add("Cardio exercises");
        } else if (goal.equals("strength")) {
            recommendations.add("Weight lifting");
        }

        if (favoriteSport.equals("team sports")) {
            recommendations.add("Soccer game");
        }

        return recommendations;
    }
}
