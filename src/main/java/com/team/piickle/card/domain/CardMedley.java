package com.team.piickle.card.domain;

import java.util.List;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Document(collection = "cardmedleys")
public class CardMedley {
    @Id private String id;
    private String title;
    private String sticker;
    private String description;
    private List<String> previewCards;
    private List<String> cards;
    private long v;
    private String coverTitle;
}
