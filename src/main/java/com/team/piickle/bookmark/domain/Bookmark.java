package com.team.piickle.bookmark.domain;

import com.team.piickle.card.domain.Card;
import com.team.piickle.common.domain.BaseEntity;
import com.team.piickle.user.domain.User;
import javax.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AttributeOverride(name = "id", column = @Column(name = "BOOKMARK_ID"))
@Builder
@Entity
public class Bookmark extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CARD_ID")
    private Card card;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User user;

    @Builder
    public Bookmark(Card card, User user) {
        this.card = card;
        this.user = user;
    }
}
