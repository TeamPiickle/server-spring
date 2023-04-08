package com.team.piickle.card.domain;

import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Document(collection = "cards")
public class Card {

    @Transient public static final String SEQUENCE_NAME = "users_sequence";
    @Id private String id;
    private String content;
    private List<String> tags = new ArrayList<>();
    private List<ObjectId> category = new ArrayList<>();
    ;
    private List<String> filter = new ArrayList<>();
    ;
    private String createdAt;
    private String updatedAt;
    private long v;

    @Builder
    public Card(
            String id,
            String content,
            List<String> tags,
            List<ObjectId> category,
            List<String> filter,
            String createdAt,
            String updatedAt,
            long v) {
        this.id = id;
        this.content = content;
        this.tags = tags;
        this.category = category;
        this.filter = filter;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.v = v;
    }
}
