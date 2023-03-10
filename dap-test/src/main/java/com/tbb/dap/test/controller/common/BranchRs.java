package com.tbb.dap.test.controller.common;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BranchRs {

    @Schema(description = "分行代號")
    private String branchId;

    @Schema(name = "分行名稱-中")
    private String branchName;

    @Schema(name = "分行名稱-英")
    private String branengname;
}
