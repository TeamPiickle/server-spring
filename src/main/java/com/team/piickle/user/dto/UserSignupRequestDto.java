package com.team.piickle.user.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserSignupRequestDto {

    private String email;
    private String nickname;
    private String birthday;
    private String password;
}
