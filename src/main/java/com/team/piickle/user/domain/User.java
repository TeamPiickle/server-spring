package com.team.piickle.user.domain;

import java.util.List;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SuperBuilder
@Document(collection = "users")
public class User {

    @Id private String id;
    private String email;
    private String hashedPassword;
    private String nickname;
    private String birthday;
    private String gender;
    private String profileImageUrl;
    private List<String> cardIdList;
    private String createdAt;
    private String updatedAt;
    private long v;

    public void update(User nicknameChangedUser) {
        this.email = nicknameChangedUser.email;
        this.nickname = nicknameChangedUser.nickname;
        this.nickname = nicknameChangedUser.nickname;
        this.gender = nicknameChangedUser.gender;
        this.hashedPassword = nicknameChangedUser.hashedPassword;
        this.profileImageUrl = nicknameChangedUser.profileImageUrl;
    }
}
