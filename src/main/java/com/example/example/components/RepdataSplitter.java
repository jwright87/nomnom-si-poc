package com.example.example.components;

import com.example.example.model.repdata.RepdataResponse;
import com.example.example.model.repdata.RepdataTask;
import org.springframework.integration.annotation.Splitter;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RepdataSplitter {



    @Splitter
    public RepdataTask[] splitRepDataTasks(Message<RepdataResponse> msg) {
        return msg.getPayload().getSurveys().toArray(new RepdataTask[0]);
    }
}
