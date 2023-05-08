package com.cloud.survey.dto.vulgarism;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class VulgarismDTO {
    private int surId;
    private boolean InfoYn;
    private boolean questionYn;
}
