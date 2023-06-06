package com.example.example.components;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.integration.core.MessageSource;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;


@AllArgsConstructor
@Component
public class SupplierMessageSource implements MessageSource<String> {



    private String origin;
    private String sourceUrl;
    private RestTemplate restTemplate;


    @Override
    public Message<String> receive() {


        ResponseEntity<String> response
                = restTemplate.getForEntity(sourceUrl, String.class);
        try {
            // Can use HttpEntity instead of String if we need headers
            var responseEntity =
                    restTemplate.getForEntity(new URI(sourceUrl),String.class);
            return MessageBuilder.withPayload(responseEntity.getBody())
                    .setHeader("ORIGIN",origin).build();
        } catch (Exception e) {
            throw new RuntimeException("Failed to read message from " + origin);
        }

    }

}
