package com.team.piickle.card.category.domain;

import com.team.piickle.common.domain.BaseEntity;

//@Getter
//@NoArgsConstructor(access = AccessLevel.PROTECTED)
//@AttributeOverride(name = "id", column = @Column(name = "CATEGORY_ID"))
//@Entity
public class Category extends BaseEntity {

    private String title;

    private String content;

    private String gradation;

    private String emoji;
//
//    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
//    private List<gory> cards = new ArrayList<>();
}
