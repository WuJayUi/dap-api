package com.tbb.dap.test.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;

@Slf4j
public class CustomErrorHandler implements ResponseErrorHandler {

    @Override
    public boolean hasError(ClientHttpResponse response) throws IOException {
        log.info("hasError res: {}", response.getBody());
        return true;
    }

    @Override
    public void handleError(ClientHttpResponse response) throws IOException {
        log.info("handleError res: {}", response.getBody());

    }
}
