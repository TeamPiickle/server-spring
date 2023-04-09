package com.team.piickle.card.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Document(collection = "bestcards")
public class BestCard {
    @Id private String id;
    private String card;
    private String createdAt;
    private String updatedAt;
    private long v;
}
