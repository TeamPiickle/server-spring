package com.team.piickle.card.category.domain;

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
@AttributeOverride(name = "id", column = @Column(name = "CATEGORY_ID"))
@Entity
public class Category extends BaseEntity {

    @Column(name = "TITLE")
    private String title;

    @Column(name = "CONTENT")
    private String content;

    @Column(name = "GRADATION")
    private String gradation;

    @Column(name = "EMOJI")
    private String emoji;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<CardCategory> cards = new ArrayList<>();
}
