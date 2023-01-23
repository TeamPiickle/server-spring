package com.team.piickle.card.domain;

import com.team.piickle.card.filter.CardFilter;
import com.team.piickle.card.tag.CardTag;
import com.team.piickle.card.category.domain.CardCategory;
import com.team.piickle.common.domain.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(mappedBy = "card", cascade = CascadeType.ALL)
    private List<CardCategory> categories = new ArrayList<>();
}
