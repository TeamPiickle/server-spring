package com.team.piickle.ballot.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Document(collection = "ballotitems")
public class BallotItem {

    @Id
    private String id;

    private String name;

    private long v;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "ballotTopicId")
//    private BallotTopic ballotTopic;

    private String ballotTopicId;
}
