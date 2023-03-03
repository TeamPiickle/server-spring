package com.team.piickle.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserProfileResponseDto {

    private String name;
    private String nickname;
    private String email;
    private String profileImageUrl;

}
