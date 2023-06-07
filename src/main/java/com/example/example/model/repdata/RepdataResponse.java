
package com.example.example.model.repdata;

import java.util.List;
import javax.annotation.Generated;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
@Generated("net.hexar.json2pojo")
public class RepdataResponse {

    @SerializedName("AccountCode")
    private String accountCode;
    @SerializedName("AccountType")
    private Long accountType;
    @SerializedName("ApiAccount")
    private String apiAccount;
    @SerializedName("ApiAccountStatus")
    private Long apiAccountStatus;
    @SerializedName("ApiMessages")
    private List<String> apiMessages;
    @SerializedName("ApiResult")
    private Long apiResult;
    @SerializedName("ApiResultCode")
    private Long apiResultCode;
    @SerializedName("ResultCount")
    private Long resultCount;
    @SerializedName("Surveys")
    private List<RepdataTask> surveys;


}
