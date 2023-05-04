package com.team.piickle.admin.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class AdminLoginDto {
    private String id;
    private String password;
}
