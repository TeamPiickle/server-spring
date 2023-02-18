package com.team.piickle.user.domain;

import com.team.piickle.bookmark.domain.Bookmark;
import com.team.piickle.common.domain.BaseEntity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AttributeOverride(name = "id", column = @Column(name = "USER_ID"))
@Builder
@AllArgsConstructor
@Table(name = "USERS")
@Entity
public class User extends BaseEntity {

    @Column private String email;

    @Column private String name;

    @Column private String nickname;

    @Enumerated(EnumType.STRING)
    @Column(name = "GENDER", nullable = false)
    private GenderStatus gender;

    @Column private String hashedPassword;

    @Column private String profileImageUrl;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Bookmark> bookmarks = new ArrayList<>();

    @Builder
    public User(Long id, LocalDateTime createdAt, LocalDateTime updatedAt, String email, String name, String nickName, GenderStatus gender, String hashedPassword, String profileImageUrl, List<Bookmark> bookmarks) {
        super(id, createdAt, updatedAt);
        this.email = email;
        this.name = name;
        this.nickName = nickName;
        this.gender = gender;
        this.hashedPassword = hashedPassword;
        this.profileImageUrl = profileImageUrl;
        this.bookmarks = bookmarks;
    }


    public void update(User nicknameChangedUser) {
        this.email = nicknameChangedUser.email;
        this.name = nicknameChangedUser.name;
        this.nickName = nicknameChangedUser.nickName;
        this.gender = nicknameChangedUser.gender;
        this.hashedPassword = nicknameChangedUser.hashedPassword;
        this.profileImageUrl = nicknameChangedUser.profileImageUrl;
        this.bookmarks = nicknameChangedUser.bookmarks;
    }
}
