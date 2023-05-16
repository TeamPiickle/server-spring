package com.team.piickle.ballot.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BallotRequestDto {
    private String userId;
    private String guestId;
    private String ballotTopicId;
    private String ballotItemId;
}
