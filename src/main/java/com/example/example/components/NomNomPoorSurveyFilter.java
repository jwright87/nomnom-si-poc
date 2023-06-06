package com.example.example.components;

import com.example.example.model.Survey;
import lombok.AllArgsConstructor;
import org.springframework.integration.annotation.Filter;
import org.springframework.messaging.Message;

import java.util.Set;

@AllArgsConstructor
public class NomNomPoorSurveyFilter {

    private Set<String> goodBuyers;

    private Integer minLoi;
    private Integer minPoints;

    @Filter
    public boolean filterPoorSurveys(Message<Survey> msg) {

        var survey = msg.getPayload();

        return goodBuyers.contains(survey.getBuyer()) && survey.getLoi() >= minLoi && survey.getPoints() >= minPoints;

    }
}
