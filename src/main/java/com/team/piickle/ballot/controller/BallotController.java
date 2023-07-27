package com.team.piickle.ballot.controller;

import com.team.piickle.auth.jwt.TokenProvider;
import com.team.piickle.ballot.dto.BallotRequestDto;
import com.team.piickle.ballot.dto.BallotStatusDto;
import com.team.piickle.ballot.dto.BallotTopicResponseDto;
import com.team.piickle.ballot.service.BallotService;
import com.team.piickle.util.StatusCode;
import com.team.piickle.util.dto.DataResponseDto;
import com.team.piickle.util.dto.ResponseDto;
import java.io.IOException;
import java.util.List;
import java.util.Locale;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/ballots")
public class BallotController {

    private final BallotService ballotService;
    private final MessageSource messageSource;
    private final TokenProvider tokenProvider;

    @PostMapping()
    private ResponseEntity<ResponseDto> ballot(@RequestBody BallotRequestDto ballotRequestDto)
            throws IOException {
        ballotService.ballot(ballotRequestDto);
        return new ResponseEntity<>(ResponseDto.of(true, StatusCode.OK), HttpStatus.OK);
    }

    @GetMapping()
    private ResponseEntity<ResponseDto> ballotList() {
        List<BallotTopicResponseDto> ballotTopicList =
                ballotService.getBallotTopicList(tokenProvider.getUserId());
        return new ResponseEntity<>(
                DataResponseDto.of(
                        ballotTopicList,
                        messageSource.getMessage("USER.PROFILE.VIEW.SUCCESS", null, Locale.getDefault())),
                HttpStatus.OK);
    }

    @GetMapping("/{ballotTopicId}")
    private ResponseEntity<ResponseDto> ballotStatus(
            @PathVariable(value = "ballotTopicId") String ballotTopicId) {
        BallotStatusDto data =
                ballotService.getBallotStatusAndUserSelect(ballotTopicId, tokenProvider.getUserId());
        return new ResponseEntity<>(DataResponseDto.of(data, "투표 현황 조회 성공"), HttpStatus.OK);
    }
}
