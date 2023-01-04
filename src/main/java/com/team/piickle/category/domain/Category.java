package com.team.piickle.category.domain;

import com.team.piickle.card.domain.Card;
import com.team.piickle.common.domain.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AttributeOverride(name = "id", column = @Column(name = "CATEGORY_ID"))
@Entity
public class Category extends BaseEntity {

    private String title;

    private String content;

    private String imgUrl;

    @OneToMany
    private List<Card> cards = new ArrayList<>();
}
