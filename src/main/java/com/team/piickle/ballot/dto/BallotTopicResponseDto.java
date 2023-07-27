package com.team.piickle.ballot.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.team.piickle.ballot.domain.BallotTopic;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class BallotTopicResponseDto {
    @JsonProperty("_id")
    private String ballotTopicId;

    private String title;

    public static BallotTopicResponseDto from(BallotTopic ballotTopic) {
        return new BallotTopicResponseDto(ballotTopic.getId(), ballotTopic.getTopic());
    }
}
