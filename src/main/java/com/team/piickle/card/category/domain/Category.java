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

    @Column
    private String title;

    @Column
    private String content;

    @Column
    private String gradation;

    @Column
    private String emoji;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<CardCategory> cards = new ArrayList<>();
}
