package com.tbb.dap.test.controller.common;


import io.swagger.v3.oas.annotations.media.Schema;

public class ApproveCase {
    @Schema(description = "流程編號")
    private String caseSN;

    public String getCaseSN() {
        return caseSN;
    }

    public void setCaseSN(String caseSN) {
        this.caseSN = caseSN;
    }
}
