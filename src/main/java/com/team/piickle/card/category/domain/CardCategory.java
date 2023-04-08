package com.team.piickle.card.category.domain;

import com.team.piickle.card.domain.Card;
import com.team.piickle.common.domain.BaseEntity;

// @Getter
// @NoArgsConstructor(access = AccessLevel.PROTECTED)
// @AttributeOverride(name = "id", column = @Column(name = "CARD_CATEGORY_ID"))
// @Entity
public class CardCategory extends BaseEntity {

    private Card card;

    private Category category;
}
