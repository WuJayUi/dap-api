package com.tbb.dap.test.controller.common;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class Status {

    @JsonProperty("Code")
    private String code;

    @JsonProperty("Desc")
    private String desc;

    @JsonProperty("Errors")
    private List<String> errors;

    @JsonProperty("TrxTime")
    private String trxTime;

}
