package com.team.piickle.ballot.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.team.piickle.ballot.domain.BallotTopic;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BallotTopicResponseDto {
    @JsonProperty("_id")
    private String ballotTopicId;
    private String title;

    public BallotTopicResponseDto(BallotTopic ballotTopic) {
        this.ballotTopicId = ballotTopic.getTopic();
        this.title = ballotTopic.getTopic();
    }
}
