package com.team.piickle.ballot.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class BallotStatusDto {

    private BallotTopic ballotTopic;
    private List<BallotItem> ballotItems;
    private String beforeTopicId;
    private String userSelect;
    private String nextTopicId;

    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    public static class BallotTopic {
        @JsonProperty("_id")
        private String id;
        private String ballotTopicContent;
    }

    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    public static class BallotItem {
        @JsonProperty("_id")
        private String id;
        private Integer status;
        private String content;
    }

    public static BallotStatusDto.BallotTopic ballotTopicOf(String id, String ballotTopicContent) {
        return new BallotTopic(id, ballotTopicContent);
    }

    public static BallotStatusDto.BallotItem ballotItemOf(String id, Integer status, String content) {
        return new BallotItem(id, status , content);
    }
}
