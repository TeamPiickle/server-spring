package com.team.piickle.ballot.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
// @Entity
// @Table(name = "ballotresults")
@Document(collection = "ballotresults")
public class BallotResult {

    @Id private String id;
    private String ballotTopicId;
    private String ballotItemId;
    private String userId;
    private String createdAt;
    private String updatedAt;
    private long v;
}
