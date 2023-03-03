package com.team.piickle.user.domain;

import com.team.piickle.bookmark.domain.Bookmark;
import com.team.piickle.common.domain.BaseEntity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AttributeOverride(name = "id", column = @Column(name = "USER_ID"))
@SuperBuilder
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


    public void update(User nicknameChangedUser) {
        this.email = nicknameChangedUser.email;
        this.name = nicknameChangedUser.name;
        this.nickname = nicknameChangedUser.nickname;
        this.gender = nicknameChangedUser.gender;
        this.hashedPassword = nicknameChangedUser.hashedPassword;
        this.profileImageUrl = nicknameChangedUser.profileImageUrl;
        this.bookmarks = nicknameChangedUser.bookmarks;
    }
}
