package com.example.example.components;

import org.apache.commons.io.IOUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.integration.core.MessageSource;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

import java.io.IOException;

public class RepdataStubMessageSource implements MessageSource<String> {

    @Override
    public Message<String> receive() {
        String payload = null;
        try {
            payload = IOUtils.toString(this.getClass().getClassLoader().getResourceAsStream(
                    "repdata-stub-response.json"));
        } catch (IOException e) {
            throw new RuntimeException("Failed to read dummy json", e);
        }

        return MessageBuilder.withPayload(payload)
                .setHeader("ORIGIN", "Repdata").build();
    }
}
