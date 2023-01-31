package com.team.piickle.ballot.domain;

import com.team.piickle.common.domain.BaseEntity;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AttributeOverride(name = "id", column = @Column(name = "BALLOT_TOPIC_ID"))
@Entity
public class BallotTopic extends BaseEntity {

    @Column
    private String topic;

    @Column
    private Long order;

    @OneToMany(mappedBy = "ballotTopic", cascade = CascadeType.ALL)
    private List<BallotItem> ballotItems = new ArrayList<>();
}
