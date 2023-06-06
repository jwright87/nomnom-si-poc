package com.example.example.model;

import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

public class P2SampleTask {

    private String surveyRequester;
    private Integer lengthInHours;
    private Integer paymentPerSurvey;
    private Integer qualifiers;
    private Integer quota;


    @AllArgsConstructor
    class timeRestrictions {

        private LocalDateTime startDate;
        private LocalDateTime expiryDate;
    }

}
