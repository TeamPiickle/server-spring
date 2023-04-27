package com.team.piickle.user.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum GenderStatus {
    MALE("MALE", "남"),
    FEMALE("FEMALE", "여"),
    ETC("ETC", "기타");

    private final String status;
    private final String message;

    public String getStatus() {
        return this.status;
    }
}
