package com.team.piickle.ballot.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Document(collection = "ballottopics")
public class BallotTopic {

    @Id private String id;

    private String topic;

    private long order;

    private long __v;

    //    @OneToMany(mappedBy = "ballotTopic", cascade = CascadeType.ALL)
    //    private List<BallotItem> ballotItems = new ArrayList<>();
}
