package com.team.piickle.ballot.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class BallotItemWithStatusDto {
    private String id;
    private int status;
    private String content;

    @Builder
    public BallotItemWithStatusDto(String id, int status, String content) {
        this.id = id;
        this.status = status;
        this.content = content;
    }
}
