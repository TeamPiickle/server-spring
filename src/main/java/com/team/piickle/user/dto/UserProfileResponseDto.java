package com.team.piickle.user.dto;

import com.team.piickle.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserProfileResponseDto {

    private String nickname;
    private String email;
    private String birthday;
    private String gender;
    private String profileImageUrl;

    public static UserProfileResponseDto toDto(User user) {
        return new UserProfileResponseDto(
                user.getNickname(),
                user.getEmail(),
                user.getBirthday(),
                user.getGender(),
                user.getProfileImageUrl());
    }

    @Override
    public String toString() {
        return "UserProfileResponseDto{"
                + "nickname='"
                + nickname
                + '\''
                + ", email='"
                + email
                + '\''
                + ", birthday='"
                + birthday
                + '\''
                + ", gender='"
                + gender
                + '\''
                + ", profileImageUrl='"
                + profileImageUrl
                + '\''
                + '}';
    }
}
