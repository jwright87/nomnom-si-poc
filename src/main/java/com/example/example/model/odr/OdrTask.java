package com.example.example.model.odr;


import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;

@Data
public class OdrTask {

    private int id;
    private int version;
    private BigDecimal amount;
    private String buttonText;
    private String category;
    private String country;
    private LocalDateTime dateCreated;
    private String description;
    private Integer loi;
    private Integer points;
    private Integer qual;
    private Integer quota;
    private String buyer;
    private String unrepresentedQuota;
    private String unrepresentedQuals;
    private Map<String, String> additionalMap;
    private Integer numberOfTimeActivated;
    private String workflowType;
    private BigDecimal margin;
    private BigDecimal exchangeRate;
}
