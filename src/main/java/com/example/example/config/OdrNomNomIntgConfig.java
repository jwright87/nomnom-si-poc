package com.example.example.config;

import com.example.example.components.FullSurveyDataTransformer;
import com.example.example.components.NomNomPoorSurveyFilter;
import com.example.example.components.SupplierMessageSource;

import com.example.example.components.UnsupportedQuoatasFilter;
import com.example.example.model.Survey;
import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.core.MessageSource;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.web.client.RestTemplate;

@AllArgsConstructor
@Configuration
public class OdrNomNomIntgConfig {



    private Gson gson;

    private NomNomPoorSurveyFilter poorSurveyFilter;
    private FullSurveyDataTransformer fullSurveyDataGatherer;
    private UnsupportedQuoatasFilter unsupportedQuoatasFilter;
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public MessageSource sampleMessageSourceOne() {
        return new SupplierMessageSource("testOrigin1", "http://localhost:4500/testOrigin1",
                restTemplate());
    }

    @Bean
    public IntegrationFlow nomNomIntgConfig() {

        return IntegrationFlows.from(sampleMessageSourceOne())
                .transform((m) -> gson.fromJson(m.toString(), Survey.class))
                .handle((m,h) -> {
                    System.out.println("Origin:" +h.get("Origin"));
                    System.out.println("Payload:" + m.toString());
                    return m;
                })
                .filter(poorSurveyFilter::filterPoorSurveys)
                .transform(fullSurveyDataGatherer::gatherFullSurveyData)
                .filter(unsupportedQuoatasFilter::filterUnsupportedQuoatas)
                .
                .get();
    }

    @Bean
    public Gson gson() {
        return new Gson();
    }
}
