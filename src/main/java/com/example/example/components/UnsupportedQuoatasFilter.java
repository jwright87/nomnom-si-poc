package com.example.example.components;

import com.example.example.model.Survey;
import lombok.AllArgsConstructor;
import org.springframework.integration.annotation.Filter;
import org.springframework.messaging.Message;

@AllArgsConstructor
public class UnsupportedQuoatasFilter {

    private Integer minQual;
    private Integer maxQuota;

    @Filter
    public boolean filterUnsupportedQuoatas(Message<Survey> msg) {

        var survey = msg.getPayload();

        return survey.getQual() > minQual && survey.getQuota() < maxQuota;

    }
}
