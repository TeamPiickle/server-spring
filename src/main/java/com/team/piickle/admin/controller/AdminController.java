package com.team.piickle.admin.controller;

import com.team.piickle.admin.dto.BookmarkedCardResponseDto;
import com.team.piickle.admin.service.AdminService;
import com.team.piickle.user.dto.UserProfileResponseDto;
import com.team.piickle.util.StatusCode;
import com.team.piickle.util.dto.DataResponseDto;
import com.team.piickle.util.dto.ResponseDto;
import java.io.IOException;
import java.util.List;
import java.util.Locale;
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
@RequestMapping("/admin")
@RestController
public class AdminController {

    private final AdminService adminService;
    private final MessageSource messageSource;

    @PostMapping(
            value = "/addExcel",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<ResponseDto> addExcel(
            @RequestPart(required = false) MultipartFile cardExcel) throws IOException {
        adminService.insertCard(cardExcel);
        return new ResponseEntity<>(ResponseDto.of(true, StatusCode.OK), HttpStatus.OK);
    }
    ;

    @GetMapping("/bookmarked")
    private ResponseEntity<ResponseDto> getCards() {
        List<BookmarkedCardResponseDto> signUpUsers = adminService.getBookmarkedCards();
        return new ResponseEntity<>(
                DataResponseDto.of(
                        signUpUsers,
                        messageSource.getMessage("USER.PROFILE.VIEW.SUCCESS", null, Locale.getDefault())),
                HttpStatus.OK);
    }

    @GetMapping("/users")
    private ResponseEntity<ResponseDto> SignUpUsers() {
        List<UserProfileResponseDto> signUpUsers = adminService.getSignUpUsers();
        return new ResponseEntity<>(
                DataResponseDto.of(
                        signUpUsers,
                        messageSource.getMessage("USER.PROFILE.VIEW.SUCCESS", null, Locale.getDefault())),
                HttpStatus.OK);
    }
}
