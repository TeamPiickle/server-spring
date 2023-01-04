package com.team.piickle.card.domain;

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

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "FILTER_ID")
    private List<Filter> filters = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "TAG_ID")
    private List<Tag> tags = new ArrayList<>();

    @OneToMany(mappedBy = "card", cascade = CascadeType.ALL)
    private List<CardCategory> categories = new ArrayList<>();
}
