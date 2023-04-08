package com.team.piickle.bookmark.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SuperBuilder
@Document(collection = "bookmarks")
public class Bookmark {

    @Id private String id;
    private ObjectId card;
    private ObjectId user;
    private String createdAt;
    private String updatedAt;
    private long v;
}
