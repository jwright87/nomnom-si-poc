
package com.example.example.model.repdata;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;
import lombok.Data;


@Data
@Generated("net.hexar.json2pojo")
public class RepdataTask {

    @SerializedName("AccountName")
    private String accountName;
    @SerializedName("BidIncidence")
    private Integer bidIncidence;
    @SerializedName("BidLengthOfInterview")
    private Integer bidLengthOfInterview;
    @SerializedName("CPI")
    private Double cPI;
    @SerializedName("CollectsPII")
    private Boolean collectsPII;
    @SerializedName("CompletionPercentage")
    private Integer completionPercentage;
    @SerializedName("Conversion")
    private Integer conversion;
    @SerializedName("CountryLanguageID")
    private Integer countryLanguageID;
    @SerializedName("FieldEndDate")
    private String fieldEndDate;
    @SerializedName("IndustryID")
    private Integer industryID;
    @SerializedName("IsOnlySupplierInGroup")
    private Boolean isOnlySupplierInGroup;
    @SerializedName("IsTrueSample")
    private Boolean isTrueSample;
    @SerializedName("LengthOfInterview")
    private Integer lengthOfInterview;
    @SerializedName("OverallCompletes")
    private Integer overallCompletes;
    @SerializedName("SampleTypeID")
    private Object sampleTypeID;
    @SerializedName("StudyTypeID")
    private Integer studyTypeID;
    @SerializedName("SurveyGroup")
    private Object surveyGroup;
    @SerializedName("SurveyGroupExists")
    private Integer surveyGroupExists;
    @SerializedName("SurveyGroupID")
    private Object surveyGroupID;
    @SerializedName("SurveyMobileConversion")
    private Integer surveyMobileConversion;
    @SerializedName("SurveyName")
    private String surveyName;
    @SerializedName("SurveyNumber")
    private Integer surveyNumber;
    @SerializedName("SurveyQuotaCalcTypeID")
    private Integer surveyQuotaCalcTypeID;
    @SerializedName("SurveySID")
    private String surveySID;
    @SerializedName("TerminationLengthOfInterview")
    private Integer terminationLengthOfInterview;
    @SerializedName("TotalRemaining")
    private Integer totalRemaining;

}
