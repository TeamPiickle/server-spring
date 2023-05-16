package com.team.piickle.user.controller;

import com.team.piickle.auth.jwt.TokenProvider;
import com.team.piickle.user.domain.User;
import com.team.piickle.user.dto.UserBookmarkedResponseDto;
import com.team.piickle.user.dto.UserProfileResponseDto;
import com.team.piickle.user.dto.UserSignupRequestDto;
import com.team.piickle.user.repository.UserRepository;
import com.team.piickle.user.service.UserService;
import com.team.piickle.util.StatusCode;
import com.team.piickle.util.dto.DataResponseDto;
import com.team.piickle.util.dto.ResponseDto;
import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/users")
@RestController
public class UserController {
    private final UserService userService;
    private final MessageSource messageSource;

    private final TokenProvider tokenProvider;

    private final UserRepository userRepository;

    @GetMapping("/test")
    private ResponseEntity<ResponseDto> test() {
        List<User> all = userRepository.findAll();
        return new ResponseEntity<>(
                DataResponseDto.of(
                        userRepository.findAll().size(),
                        messageSource.getMessage("USER.PROFILE.VIEW.SUCCESS", null, Locale.getDefault())),
                HttpStatus.OK);
    }

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    private ResponseEntity<ResponseDto> signup(
            @RequestBody UserSignupRequestDto userSignupRequestDto,
            @RequestPart(required = false) MultipartFile profileImageFile)
            throws IOException {
        userService.signup(userSignupRequestDto, profileImageFile);
        return new ResponseEntity<>(ResponseDto.of(true, StatusCode.OK), HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    private ResponseEntity<ResponseDto> user(@PathVariable("userId") String userId) {
        UserProfileResponseDto userProfileResponseDto = userService.getUser(userId);
        return new ResponseEntity<>(
                DataResponseDto.of(
                        userProfileResponseDto,
                        messageSource.getMessage("USER.PROFILE.VIEW.SUCCESS", null, Locale.getDefault())),
                HttpStatus.OK);
    }

    @GetMapping("/nickname/is-exist")
    private ResponseEntity<ResponseDto> nicknameDuplicationCheck(@RequestParam String nickname) {
        userService.nicknameDuplicationCheck(nickname);
        return new ResponseEntity<>(ResponseDto.of(true, StatusCode.OK), HttpStatus.OK);
    }

    @PatchMapping("/nickname")
    private ResponseEntity<ResponseDto> updateNickname(@RequestBody Map<String, String> nickname) {
        userService.updateNickname(tokenProvider.getUserId(), nickname.get("nickname"));
        return new ResponseEntity<>(ResponseDto.of(true, StatusCode.OK), HttpStatus.OK);
    }

    @PutMapping("/me")
    private ResponseEntity<ResponseDto> deleteUser() {
        userService.deleteUser(tokenProvider.getUserId());
        return new ResponseEntity<>(ResponseDto.of(true, StatusCode.OK), HttpStatus.OK);
    }

    @PatchMapping("/password")
    private ResponseEntity<ResponseDto> changePassword(@RequestBody Map<String, String> password) {
        userService.updatePassword(tokenProvider.getUserId(), password.get("password"));
        return new ResponseEntity<>(ResponseDto.of(true, StatusCode.OK), HttpStatus.OK);
    }

    @PatchMapping("/profile-image")
    private ResponseEntity<ResponseDto> updateProfileImage(
            @RequestPart(required = false) MultipartFile profileImageFile) throws IOException {
        userService.updateProfileImage(tokenProvider.getUserId(), profileImageFile);
        return new ResponseEntity<>(ResponseDto.of(true, StatusCode.OK), HttpStatus.OK);
    }

    @GetMapping("/bookmarks")
    private ResponseEntity<ResponseDto> getBookmarks() {
        List<UserBookmarkedResponseDto> data = userService.getBookmarks(tokenProvider.getUserId());
        return new ResponseEntity<>(
                DataResponseDto.of(
                        data,
                        "유저 북마크 조회 성공"),
                HttpStatus.OK);
    }

    @PutMapping("/bookmarks")
    private ResponseEntity<ResponseDto> bookmark(@RequestBody Map<String, String> cardId) {
        String BookmarkedCardId = userService.changeBookmark(tokenProvider.getUserId(), cardId.get("cardId"));
        return new ResponseEntity<>(
                DataResponseDto.of(
                        BookmarkedCardId,
                        messageSource.getMessage("USER.PROFILE.VIEW.SUCCESS", null, Locale.getDefault())),
                HttpStatus.OK);
    }
}
