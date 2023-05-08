package com.cloud.survey.service;

import com.cloud.survey.dto.vulgarism.VulgarismDTO;
import com.cloud.survey.entity.SurveyVulgarism;
import com.cloud.survey.repository.VulgarismRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.beans.Transient;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SurveyVulgarismServiceImpl implements SurveyVulgarismService {

    @Autowired
    private final VulgarismRepository vulgarismRepository;

    @Override
    @Transient
    public void InsertVulgarism(int surID, boolean InfoYn, boolean QuestionYn){
        SurveyVulgarism vulgarism = SurveyVulgarism.builder()
                .surId(surID)
                .infoYn(InfoYn)
                .questionYn(QuestionYn)
                .build();
        vulgarismRepository.save(vulgarism);
    }

    @Override
    public List<VulgarismDTO> getVulgarismList(){
        List<SurveyVulgarism> vulgarismList = vulgarismRepository.findAll();
        List<VulgarismDTO> vulgarismDTOList = new ArrayList<>();
        vulgarismList.forEach(vulgarism ->{
            VulgarismDTO dto = VulgarismDTO.builder()
                    .surId(vulgarism.getSurId())
                    .InfoYn(vulgarism.isInfoYn())
                    .questionYn(vulgarism.isQuestionYn())
                    .build();
            vulgarismDTOList.add(dto);
        });
        return vulgarismDTOList;
    };


}
