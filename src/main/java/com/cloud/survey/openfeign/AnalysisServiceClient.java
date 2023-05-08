package com.cloud.survey.openfeign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "analysis-client", url="localhost:9000/analysis-service/v1/analysis", configuration = FeignConfig.class)
public interface AnalysisServiceClient {
    @GetMapping(value = "/survey_analysis_info?sur_id={surId}")
    List<Object> getTypeSubjectList(@PathVariable int surId);

}


