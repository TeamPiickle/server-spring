package com.team.piickle.ballot.domain;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Document(collection = "ballotresults")
public class BallotResult {

    @Id private String id;
    private String ballotTopicId;
    private String ballotItemId;
    private String userId;
    private String guestId;
    @CreatedDate
    private LocalDateTime createdAt;
    @LastModifiedDate
    private LocalDateTime updatedAt;
    private long v;

    public BallotResult update(String ballotItemId) {
        this.ballotItemId = ballotItemId;
        return this;
    }
}
