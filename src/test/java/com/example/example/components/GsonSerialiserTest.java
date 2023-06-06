package com.example.example.components;

import com.example.example.model.repdata.RepdataResponse;
import com.example.example.model.repdata.RepdataTask;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.*;

class GsonSerialiserTest {


    private Gson gson;


    @BeforeEach
    public void setup() {
        gson = (new Gson());
    }

    @Test
    void shouldSerialiseRepdataTaskIntoPojo() throws IOException {
        String taskJson = IOUtils.toString(this.getClass().getClassLoader().getResourceAsStream("repdata/repdata-task.json"));
        RepdataTask repdataTask = new Gson().fromJson(taskJson, RepdataTask.class);
        assertThat(repdataTask.getAccountName()).isEqualTo("Branded Research");
    }

    @Test
    void shouldSerialiseFullResponseIntoPojo() throws IOException {
        String fullJson = IOUtils.toString(this.getClass().getClassLoader().getResourceAsStream(
                "repdata/repdata-response.json"));
        RepdataResponse repdataResponse = new Gson().fromJson(fullJson, RepdataResponse.class);
        assertThat(repdataResponse.getSurveys().size()).isGreaterThan(10);
    }
}