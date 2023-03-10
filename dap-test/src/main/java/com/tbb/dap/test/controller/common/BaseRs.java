package com.tbb.dap.test.controller.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.Gson;
import com.tbb.dap.common.exception.BaseException;
import com.tbb.dap.common.utils.DateUtil;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Date;
import java.util.Map;

@Data
@Slf4j
public class BaseRs<T> {

    @JsonProperty("Status")
    private Status status;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("Body")
    private T body;

    public BaseRs(T body, Status status) {
        this.body = body;
        this.status = status;
    }

    public BaseRs(String errCode, String errMsg) {
        Status status1 = new Status();
        status1.setCode(errCode);
        status1.setDesc(errMsg);
        this.status = status1;
    }

    public static <E> ResponseEntity<BaseRs<E>> ok(E e) {
        Status status1 = new Status();
        status1.setDesc("交易成功");
        status1.setCode("0");
        status1.setTrxTime(DateUtil.format2yyyyMMddHHmmss(new Date()));
        return ResponseEntity.ok(new BaseRs<>(e, status1));
    }

    public static <E> ResponseEntity<BaseRs<E>> ok(E e, HttpHeaders headers, HttpStatus status) {
        Status status1 = new Status();
        status1.setDesc("交易成功");
        status1.setCode("0");
        status1.setTrxTime(DateUtil.format2yyyyMMddHHmmss(new Date()));
        return new ResponseEntity<>(new BaseRs<>(e, status1), headers, status);
    }

    public static <E> ResponseEntity<BaseRs<E>> ok(E e, HttpStatus status) {
        Status status1 = new Status();
        status1.setDesc("交易成功");
        status1.setCode("0");
        status1.setTrxTime(DateUtil.format2yyyyMMddHHmmss(new Date()));
        return new ResponseEntity<>(new BaseRs<>(e, status1), status);
    }

    public static <N> ResponseEntity<BaseRs<N>> error(BaseException e) {
        return ResponseEntity.ok(new BaseRs<>(e.getErrCode(), e.getMessage()));
    }
    public static <N> ResponseEntity<BaseRs<N>> error(Exception e) {
        return ResponseEntity.ok(new BaseRs<>("sys9999", "系統異常"));
    }
    public static <N> ResponseEntity<BaseRs<N>> error(BaseException e, HttpStatus httpStatus) {
        return new ResponseEntity<>(new BaseRs<>(e.getErrCode(), e.getMessage()), httpStatus);
    }

    public static <N> ResponseEntity<BaseRs<N>> error(RuntimeException e, HttpStatus httpStatus) {
        return new ResponseEntity<>(new BaseRs<>("9999", e.getMessage()), httpStatus);
    }

    public static ResponseEntity<Object> error(Map<Object, Object> e, HttpStatus httpStatus) {
        Gson gson = new Gson();

        String errJson = gson.toJson(e.values());
        log.info("errJson: {}", errJson);
        return new ResponseEntity<>(new BaseRs<>("9999", errJson), httpStatus);
    }
}
