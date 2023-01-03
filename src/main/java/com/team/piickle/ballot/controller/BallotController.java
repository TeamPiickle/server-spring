package com.team.piickle.ballot.controller;

import com.team.piickle.ballot.service.BallotService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class BallotController {

    private final BallotService ballotService;
}
