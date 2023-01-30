package com.team.piickle.user.service;

import com.team.piickle.auth.jwt.TokenProvider;
import com.team.piickle.common.exception.GeneralException;
import com.team.piickle.user.domain.GenderStatus;
import com.team.piickle.user.domain.User;
import com.team.piickle.user.dto.UserLoginRequestDto;
import com.team.piickle.user.dto.UserSignupRequestDto;
import com.team.piickle.user.repository.UserRepository;
import com.team.piickle.util.StatusCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private final TokenProvider tokenProvider;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username).orElseThrow(() -> new GeneralException("User not found in the database"));
        if (user == null) {
            log.error("User not found in the database");
            throw new GeneralException("User not found in the database");
        } else {
            log.info("User found in the database: {}", username);
        }
//        List<GrantedAuthority> authorities = user.getRoles().stream()
//                .map(role ->
//                        new SimpleGrantedAuthority(role.getName()))
//                .collect(Collectors.toList());
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getHashedPassword(), authorities);
    }

    @Transactional
    public void signup(UserSignupRequestDto userSignupRequest) {
        log.info(userSignupRequest.getEmail());
        if (userRepository.existsByEmail(userSignupRequest.getEmail())) {
            throw new GeneralException(StatusCode.VALIDATION_ERROR, "이미 존재하는 이메일 입니다.");
        }
       userRepository.save(User.builder()
                .email(userSignupRequest.getEmail())
                .hashedPassword(passwordEncoder.encode(userSignupRequest.getPassword()))
                .gender(GenderStatus.MALE)
                .build());
    }
}
