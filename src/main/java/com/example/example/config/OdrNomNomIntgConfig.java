package com.example.example.config;

import com.example.example.components.*;
import com.example.example.model.odr.OdrTask;
import com.example.example.model.repdata.RepdataResponse;
import com.example.example.model.repdata.RepdataTask;
import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.integration.annotation.Transformer;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.core.MessageSource;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.MessageChannels;
import org.springframework.integration.dsl.Pollers;
import org.springframework.integration.handler.LoggingHandler;
import org.springframework.messaging.Message;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
@AllArgsConstructor
@Configuration
public class OdrNomNomIntgConfig {


    private static final int POLLING_DELAY_IN_MILLISECONDS = 1000;

    private Gson gson;

    private NomNomPoorSurveyFilter poorSurveyFilter;
    private FullSurveyDataTransformer fullSurveyDataGatherer;
    private UnsupportedQuotasFilter unsupportedQuotasFilter;

    private OdrSurveyMappingTransformers surveyMappingTransformers;

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public MessageSource sampleMessageSourceOne() {
        return new SupplierMessageSource("testOrigin1", "http://localhost:4500/testOrigin1",
                restTemplate());
    }

    LoggingHandler loggingHandler() {
        return new LoggingHandler("DEBUG");
    }

    @Bean
    public DirectChannel savantaChannel() {
        return new DirectChannel();
    }

    @Bean
    public DirectChannel p2Chennel() {
        return new DirectChannel();
    }

    private RepdataStubMessageSource repdataStubMessageSource;

    private RepdataSplitter repdataSplitter;


    @Transformer
    public RepdataTask[] extractTasks(Message<RepdataResponse> msg) {
        return msg.getPayload().getSurveys().toArray(new RepdataTask[0]);
    }

    @Bean
    public IntegrationFlow nomNomInitIntgFlow() {

        return IntegrationFlow.from(repdataStubMessageSource, c -> c.poller(Pollers.fixedRate(POLLING_DELAY_IN_MILLISECONDS)))
                .log(LoggingHandler.Level.INFO,"Starting Flow...")
                .handle((m) -> m.getHeaders().put("ORIGINAL_MESSAGE",m.getPayload()))
                .enrichHeaders((m) -> {
                    m.header("DATETIME_INGESTED", LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
                })
                .transform(this::extractTasks)
                .split()
                .channel(MessageChannels.executor(threadPoolTaskExecutor()))
                .filter(poorSurveyFilter::filterPoorSurveys)
                .transform(fullSurveyDataGatherer::gatherFullSurveyData)
                .filter(unsupportedQuotasFilter::filterUnsupportedQuoatas)
                .log(LoggingHandler.Level.INFO,message -> message.getPayload())
                .get();
    }

    @Bean
    public TaskExecutor threadPoolTaskExecutor() {
        int poolSize = 20;
        log.debug("...... creating ThreadPool of size {}.", poolSize);
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setThreadNamePrefix("Nom_Nom_Thread_");
        //TODO expose via JMX? may be worth it for optimising before releasing to prod.
        executor.setMaxPoolSize(poolSize);
        executor.setCorePoolSize(poolSize);
//        executor.setQueueCapacity(22);
        return executor;
    }

    @Bean
    public IntegrationFlow nomnomMappingIntgrationFlow() {
        return null;
    }

    @Bean
    public Gson gson() {
        return new Gson();
    }
}
