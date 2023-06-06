package com.example.example.components;

import ch.qos.logback.core.joran.sanity.Pair;
import com.example.example.model.Survey;
import lombok.AllArgsConstructor;
import org.springframework.integration.annotation.Transformer;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.LocalDateTime;

@AllArgsConstructor
public class FullSurveyDataTransformer {


    private RestTemplate restTemplate;

    @Transformer
    public Message<Survey> gatherFullSurveyData(Message<Survey> msg) {

        LocalDateTime extra1 = LocalDateTime.now();
        String extra2 = "Spring Intg Test ODR Message";
        var survey = msg.getPayload();
        survey.setOdrSpecificData1(extra1.toString());
        survey.setOdrSpecificData2(extra2.toString());
        return MessageBuilder.fromMessage(msg).build();
    }



}
