package com.example.example.components;

import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import org.springframework.integration.annotation.Transformer;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;
import java.util.LinkedList;
import java.util.List;

import com.google.gson.reflect.TypeToken;

@AllArgsConstructor
@Component
public class GsonSerialiser {

    private Gson gson;

    @Transformer
    public <T> Message<T> serialise(Message<String> msg) {
        T t = serialiseTypeFromJson(msg);
        return MessageBuilder.withPayload(t).copyHeaders(msg.getHeaders()).build();
    }

    @Transformer
    public <T> T serialise(String json,Class<T> clazz) {
        return gson.fromJson(json,new TypeToken<T>() {}.getType());
    }

    private  <T> T serialiseTypeFromJson(Message<String> msg) {
        Type tType = new TypeToken<T>() {}.getType();
        T t  = gson.fromJson(msg.getPayload(),tType);
        return t;
    }

     <T> List<T> serialiseListFromJson(Message<String> msg) {
        Type listType = new TypeToken<LinkedList<T>>() {}.getType();
        List<T> yourClassList = gson.fromJson(msg.getPayload(), listType);
        return yourClassList;
    }
}
