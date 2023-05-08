package com.cloud.survey.openfeign;

import com.cloud.survey.dto.user.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@FeignClient(name = "auth-client", url="localhost:9000/auth-service/v1/user", configuration = FeignConfig.class)
public interface AuthServiceClient {
    @GetMapping(value = "/detail?user_id={UserId}")
    UserDTO getUserDetailInfo(@PathVariable String UserId);

    @PostMapping(value = "/survey_users_analysis")
    List<UserDTO> getUserDetailInfoList(@RequestBody List<Map<String, Object>> list); // 설문조사 피설문자 정보 리스트 조회

}
