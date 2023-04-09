package com.team.piickle.card.domain;

import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Document(collection = "cards")
public class Card {

    @Transient public static final String SEQUENCE_NAME = "users_sequence";
    @Id private String id;
    private String content;
    private List<String> filter = new ArrayList<>();
    private List<String> tags = new ArrayList<>();
    private List<ObjectId> category = new ArrayList<>();
    private String createdAt;
    private String updatedAt;
    private long v;
}
