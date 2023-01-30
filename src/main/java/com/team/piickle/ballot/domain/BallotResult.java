package com.team.piickle.ballot.domain;

import com.team.piickle.common.domain.BaseEntity;
import com.team.piickle.user.domain.User;
import javax.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AttributeOverride(name = "id", column = @Column(name = "BALLOT_RESULT_ID"))
@Entity
public class BallotResult extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BALLOT_ITEM_ID")
    private BallotItem ballotItem;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BALLOT_TOPIC_ID")
    private BallotTopic ballotTopic;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User user;
}
