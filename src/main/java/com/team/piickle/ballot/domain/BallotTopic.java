package com.team.piickle.ballot.domain;

import com.team.piickle.common.domain.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AttributeOverride(name = "id", column = @Column(name = "BALLOT_TOPIC_ID"))
@Entity
public class BallotTopic extends BaseEntity {

    @Column(name = "TOPIC")
    private String topic;

    @OneToMany(mappedBy = "ballotTopic", cascade = CascadeType.ALL)
    private List<BallotItem> ballotItems = new ArrayList<>();
}
