package com.team.piickle.bookmark.domain;

import com.team.piickle.card.domain.Card;
import com.team.piickle.common.domain.BaseEntity;
import com.team.piickle.user.domain.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SuperBuilder
@AttributeOverride(name = "id", column = @Column(name = "BOOKMARK_ID"))
@Entity
public class Bookmark extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CARD_ID")
    private Card card;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User user;

}
