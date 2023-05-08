package com.cloud.survey.dto.user;



import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@RequiredArgsConstructor
@Getter
public enum UserRole {

    ADMIN("관리자"),
    USER("사용자");

    private final String title;
    public String getCode() {
        return name();
    }
}
