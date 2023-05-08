package com.cloud.survey.controller;

import com.cloud.survey.dto.answer.AnswerDTO;
import com.cloud.survey.dto.answer.AnswerQuestionDTO;
import com.cloud.survey.dto.answer.AnswerRequestDTO;
import com.cloud.survey.service.AnswerService;
import com.cloud.survey.service.kafka.producer.KafkaProducer;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Log4j2
@RestController
@RequestMapping(value="v1/answer")
public class AnswerController {
    @Autowired
    private AnswerService answerService;

    @Autowired
    private KafkaProducer kafkaProducer;


    // 사용자의 답변 조회
    @RequestMapping(value = "/user_answer", method = RequestMethod.POST)
    public ResponseEntity<List<Map<String,Object>>> getSurveyAnswer(@RequestParam(value = "user_id") String userId, @RequestParam(value = "sur_id") int surId) {
        return new ResponseEntity<>(answerService.getUserAnswer(userId, surId), HttpStatus.OK);
    }

    @PostMapping("list")
    public ResponseEntity<List<AnswerQuestionDTO>> getAnswerList(@RequestParam String regId, @RequestParam Integer surId) {
        List<AnswerQuestionDTO> answerList = answerService.getAnswerList(surId, regId);
        return new ResponseEntity<>(answerList, HttpStatus.OK);
    }

    // 답변 등록
    @Transactional
    @PostMapping("/reg")
    public ResponseEntity<String> registerAnswer(Principal principal, @RequestBody(required = false) AnswerRequestDTO answerResponseDTO) {


        Integer surId = answerResponseDTO.getSurId();
        List<AnswerDTO> answerDTOList = answerResponseDTO.getAnswerDTOList();
        answerService.insertAnswer(surId, answerDTOList);

        // 포인트 적립 토픽 생성
        if(principal != null) {
            JwtAuthenticationToken token = (JwtAuthenticationToken) principal;
            Map<String, String> accruePointMap = new HashMap<>();
            accruePointMap.put("user_id", token.getTokenAttributes().get("preferred_username").toString());
            accruePointMap.put("sur_id", String.valueOf(surId));
            accruePointMap.put("type", "E");
            Map<String, Object> map = new HashMap<>();
            map.put("accrue_point_map", accruePointMap);
            kafkaProducer.sendObjetMap("ANSWER_POINT", map);
        }
        // 설문조사 피설문자 통계 토픽 생성
        kafkaProducer.sendObjetMap("ANSWER_ANALYSIS",answerService.getSurveyAnswerAnalysis(surId));


        return new ResponseEntity<>("Success", HttpStatus.OK);
    }

    // 답변 수정
    @PostMapping("/mod")
    public ResponseEntity<String> modifyAnswer(@RequestBody(required = false) AnswerRequestDTO answerRequestDTO) {
        Integer surId = answerRequestDTO.getSurId();
        List<AnswerDTO> answerDTOList = answerRequestDTO.getAnswerDTOList();
        answerService.updateAnswer(surId, answerDTOList);
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }

    // 답변 삭제
    @PostMapping("/del")
    public ResponseEntity<String> removeAnswer(
            @RequestParam(value = "surId") Integer surId,
            @RequestParam(value = "regId") String regId) {
        // ansId 필요
        answerService.deleteAnswer(surId, regId);
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }

}
