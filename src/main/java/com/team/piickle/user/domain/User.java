package com.team.piickle.user.domain;

import com.team.piickle.bookmark.domain.Bookmark;
import com.team.piickle.card.domain.Card;
import com.team.piickle.common.domain.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AttributeOverride(name = "id", column = @Column(name = "USER_ID"))
@Entity
public class User extends BaseEntity {

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "NAME")
    private String name;

    @Column(name = "NICKNAME")
    private String nickName;

    @Enumerated(EnumType.STRING)
    @Column(name = "GENDER", nullable = false)
    private GenderStatus gender;

    @Column(name = "HASHED_PASSWORD")
    private String hashedPassword;

    @Column(name = "PROFILE_IMAGE_URL")
    private String profileImageUrl;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Bookmark> bookmarks = new ArrayList<>();
}
