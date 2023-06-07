package com.example.example.components;

import com.example.example.model.OdrTask;
import com.example.example.model.SavantaTask;
import org.springframework.integration.annotation.Transformer;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;

public class OdrFormatTransformer {



    @Transformer
    public Message<OdrTask> transform(Message<SavantaTask> msg) {
        return MessageBuilder.withPayload(new OdrTask(),new MessageHeaders());
    }
}
