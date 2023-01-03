package com.team.piickle.preuser.controller;

import com.team.piickle.preuser.service.PreUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class PreUserController {

    private final PreUserService preUserService;
}
