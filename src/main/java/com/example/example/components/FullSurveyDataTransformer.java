package com.example.example.components;

import com.example.example.model.odr.OdrTask;
import com.example.example.model.repdata.RepdataTask;
import lombok.AllArgsConstructor;
import org.springframework.integration.annotation.Transformer;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;

@AllArgsConstructor
public class FullSurveyDataTransformer {


    private RestTemplate restTemplate;

    @Transformer
    public Message<OdrTask> gatherFullSurveyData(Message<RepdataTask> msg) {

        System.out.println("Mapping Repdata Survey to ODR Task...");
        var repdataTask = msg.getPayload();
        var odrTask = new OdrTask();
        odrTask.setLoi(repdataTask.getLengthOfInterview());
//        odrTask.set(repdataTask.getCPI());
        //TODO call external http service
        return MessageBuilder.withPayload(odrTask).copyHeaders(msg.getHeaders()).build();
    }



}
