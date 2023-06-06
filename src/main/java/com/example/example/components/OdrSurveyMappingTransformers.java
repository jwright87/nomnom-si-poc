package com.example.example.components;

import com.example.example.model.odr.OdrTask;
import com.example.example.model.P2SampleTask;
import com.example.example.model.SatantaTask;
import org.springframework.integration.annotation.Transformer;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Component
public class OdrSurveyMappingTransformers {


    @Transformer
    public Message<OdrTask> transformFromSavanta(Message<SatantaTask> savantaSurvey) {
        var odr = new OdrTask();
        //TODO mapping
        return MessageBuilder.withPayload(odr).copyHeaders(savantaSurvey.getHeaders()).build();
    }

    @Transformer
    public Message<OdrTask> transformFromP2(Message<P2SampleTask> p2Survey) {
        var odr = new OdrTask();
        //TODO mapping
        return MessageBuilder.withPayload(odr).copyHeaders(p2Survey.getHeaders()).build();
    }
}
