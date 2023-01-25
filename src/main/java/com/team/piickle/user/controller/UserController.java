package com.team.piickle.user.controller;

import com.team.piickle.auth.jwt.TokenProvider;
import com.team.piickle.user.domain.User;
import com.team.piickle.user.dto.UserLoginRequestDto;
import com.team.piickle.user.dto.UserSignupRequestDto;
import com.team.piickle.user.service.UserService;
import com.team.piickle.util.StatusCode;
import com.team.piickle.util.dto.DataResponseDto;
import com.team.piickle.util.dto.ResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/users")
@RestController
public class UserController {

    public static final String AUTHORIZATION_HEADER = "Authorization";
    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final UserService userService;

    @PostMapping()
    private ResponseEntity<ResponseDto> signup(@RequestBody UserSignupRequestDto userSignupRequestDto) {
        userService.signup(userSignupRequestDto);
        return new ResponseEntity<>(ResponseDto.of(true, StatusCode.OK), HttpStatus.OK);
    }

    @PostMapping("/login")
    private ResponseEntity<ResponseDto> login(@RequestBody UserLoginRequestDto userLoginRequestDto) {
//        try {
//            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userLoginRequestDto.getEmail(), userLoginRequestDto.getPassword());
//            Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
//            SecurityContextHolder.getContext().setAuthentication(authentication);
//
//            String jwt = tokenProvider.createToken(authentication);
//            HttpHeaders httpHeaders = new HttpHeaders();
//            httpHeaders.add(AUTHORIZATION_HEADER, "Bearer " + jwt);
//            return new ResponseEntity<>(DataResponseDto.of("Bearer " + jwt), HttpStatus.OK);
//        } catch (Exception e) {
//            log.info(e.getMessage());
//        }
        return new ResponseEntity<>(DataResponseDto.of("Bearer " ), HttpStatus.OK);
    }
}
