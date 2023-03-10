package com.tbb.dap.test.controller.common;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DataRs {
	@Schema(description = "功能類別")
	private String funcType;
	@Schema(description = "參數類型")
	private String paramType;
	@Schema(description = "參數說明")
	private String paramDesc;
	@Schema(description = "代碼")
	private String code;
	@Schema(description = "說明")
	private String description;
}
