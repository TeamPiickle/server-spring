package com.team.piickle.user.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Document(collection = "users")
public class User {

    @Id private String id;
    private String email;
    private String name;
    private String nickname;
    private String hashedPassword;
    private String profileImageUrl;

    @Builder.Default
    private List<String> cardIdList = new ArrayList<>();

    private String birthday;
    private String gender;

    @CreatedDate
    private LocalDateTime createdAt;
    @LastModifiedDate
    private LocalDateTime updatedAt;
    private long v;

    @Builder
    public User(String id, String email, String name, String nickname, String hashedPassword, String profileImageUrl, List<String> cardIdList, String birthday, String gender, LocalDateTime createdAt, LocalDateTime updatedAt, long v) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.nickname = nickname;
        this.hashedPassword = hashedPassword;
        this.profileImageUrl = profileImageUrl;
        this.cardIdList = cardIdList;
        this.birthday = birthday;
        this.gender = gender;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.v = v;
    }

    public void update(User user) {
        this.email = user.email;
        this.nickname = user.nickname;
        this.gender = user.gender;
        this.hashedPassword = user.hashedPassword;
        this.profileImageUrl = user.profileImageUrl;
    }
}
