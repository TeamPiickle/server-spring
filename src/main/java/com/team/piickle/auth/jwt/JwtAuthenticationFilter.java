package com.team.piickle.auth.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.team.piickle.common.exception.GeneralException;
import com.team.piickle.user.dto.UserLoginRequestDto;
import com.team.piickle.util.dto.DataResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final TokenProvider tokenProvider;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        log.info("로그인 시도 : jwtAuthenticationFilter.attemptAuthentication");
        try {
            UserLoginRequestDto userLoginRequestDto = new ObjectMapper().readValue(request.getReader(), UserLoginRequestDto.class);
            log.info(userLoginRequestDto.getEmail());
            log.info(userLoginRequestDto.getPassword());
//            String username = request.getParameter("email");
//            String password = request.getParameter("password");
//            log.info("Username is : {}", username);
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userLoginRequestDto.getEmail(), userLoginRequestDto.getPassword());
            log.info(usernamePasswordAuthenticationToken.getCredentials().toString());
            return authenticationManagerBuilder.getObject().authenticate(usernamePasswordAuthenticationToken);
        } catch (IOException e) {
            throw new GeneralException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws ServletException, IOException {
        User user = (User) authResult.getPrincipal();
        String token = tokenProvider.createToken(authResult);
        response.addHeader("Authorization", "Bearer " + token);
        String refresh_token = tokenProvider.createRefreshToken(authResult);
        Map<String, String> tokens = new HashMap<>();
        tokens.put("_id", user.getUsername());
        tokens.put("access_token", token);
        tokens.put("refresh_token", refresh_token);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        new ObjectMapper().writeValue(response.getOutputStream(), DataResponseDto.of(tokens));
    }
}