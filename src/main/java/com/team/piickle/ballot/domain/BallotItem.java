package com.team.piickle.ballot.domain;

import com.team.piickle.common.domain.BaseEntity;
import javax.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AttributeOverride(name = "id", column = @Column(name = "BALLOT_ITEM_ID"))
@Entity
public class BallotItem extends BaseEntity {

    @Column private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BALLOT_TOPIC_ID")
    private BallotTopic ballotTopic;
}
