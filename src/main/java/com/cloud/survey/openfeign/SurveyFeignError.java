package com.cloud.survey.openfeign;

import feign.FeignException;
import feign.Response;
import feign.codec.DecodeException;
import feign.codec.Decoder;
import feign.codec.ErrorDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.lang.reflect.Type;

public class SurveyFeignError implements ErrorDecoder {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Override
    public Exception decode(String s, Response response) {

        switch (response.status()) {
            case 200:
                log.info("============================200 응답 ===========================");
                log.info(response.reason().toString());
                log.info("================= header 정보");
                log.info(response.headers().toString());
            case 404:
                log.info("============================ 응답 확인 필요===========================");
                log.info(String.valueOf(response.status()));
        }

        return null;
    }
}
