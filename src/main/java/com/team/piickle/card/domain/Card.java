package com.team.piickle.card.domain;

import com.team.piickle.card.filter.CardFilter;
import com.team.piickle.card.tag.CardTag;
import com.team.piickle.common.domain.BaseEntity;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AttributeOverride(name = "id", column = @Column(name = "CARD_ID"))
@Entity
public class Card extends BaseEntity {
    @Column(name = "CONTENT")
    private String content;

    @OneToMany(mappedBy = "card", cascade = CascadeType.ALL)
    private List<CardFilter> filters = new ArrayList<>();

    @OneToMany(mappedBy = "tag", cascade = CascadeType.ALL)
    private List<CardTag> tags = new ArrayList<>();
}
