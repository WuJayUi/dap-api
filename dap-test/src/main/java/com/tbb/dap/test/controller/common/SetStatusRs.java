package com.tbb.dap.test.controller.common;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SetStatusRs {
    @Schema(description = "案件編號")
    private String caseNo;
    @Schema(description = "操作動作")
    private String witchApi;
    @Schema(description = "狀態")
    private String status;
}
