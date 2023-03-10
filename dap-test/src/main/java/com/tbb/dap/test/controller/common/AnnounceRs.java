package com.tbb.dap.test.controller.common;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnnounceRs {

    @Schema(description = "公告識別碼")
    private String announceId;

    @Schema(description = "公告狀態，Y：啟用、N：停用")
    private String status;

    @Schema(description = "系統別，D：數位申辦平台")
    private String channel;

    @Schema(description = "公告類別，C：一般公告")
    private String announceType;

    @Schema(description = "是否置頂，Y：置頂、N：不置頂")
    private String isTop;

    @Schema(description = "公告標題")
    private String topic;

    @Schema(description = "公告內容")
    private String contentData;

    @Schema(description = "內容種類，D：訊息內容為文字敘述、U：訊息內容為連結、F：訊息內容為檔案(pdf, png)")
    private String contentType;

    @Schema(description = "上架時間")
    private String startTime;

    @Schema(description = "下架時間")
    private String endTime;

    @Schema(description = "公告建立人員")
    private String createEmpId;

    @Schema(description = "是否架上")
    private String still;
}
