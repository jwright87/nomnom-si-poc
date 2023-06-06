package com.example.example.components;

import com.example.example.model.Survey;
import org.springframework.integration.annotation.Transformer;
import org.springframework.messaging.Message;

public class OdrFormatTransformer {



    @Transformer
    public Message<OdrSurvey> transform(Message<Survey> msg) {

    }
}
