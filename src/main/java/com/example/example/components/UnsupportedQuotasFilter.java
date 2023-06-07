package com.example.example.components;

import com.example.example.model.OdrTask;
import lombok.AllArgsConstructor;
import org.springframework.integration.annotation.Filter;
import org.springframework.messaging.Message;

@AllArgsConstructor
public class UnsupportedQuotasFilter {

    private Integer minQual;
    private Integer maxQuota;

    @Filter
    public boolean filterUnsupportedQuoatas(Message<OdrTask> msg) {

        var survey = msg.getPayload();

        return survey.getQual() > minQual && survey.getQuota() < maxQuota;

    }
}
