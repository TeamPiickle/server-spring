package com.team.piickle.admin.controller;

import com.team.piickle.admin.dto.BookmarkedCardResponseDto;
import com.team.piickle.admin.service.AdminService;
import com.team.piickle.util.StatusCode;
import com.team.piickle.util.dto.ResponseDto;
import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/admin")
@Controller
public class AdminController {

    private final AdminService adminService;
    private final MessageSource messageSource;

    @ModelAttribute(name = "userCount")
    private int countUser() {
        return adminService.countAllUser();
    }

    @ModelAttribute(name = "bookmarkCounts")
    private List<BookmarkedCardResponseDto> countBookmarks() {
        return adminService.getBookmarkedCards();
    }

    @GetMapping
    public String home(Model model) {
        return "dashboard";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    @ResponseBody
    public ResponseEntity<String> requestToLogin(
            @RequestBody AdminLoginDto adminLoginDto, HttpSession httpSession) {
        boolean authorized = adminService.login(adminLoginDto);
        if (authorized) {
            httpSession.setAttribute("isAdmin", true);
            return new ResponseEntity<>("Hello Admin", HttpStatus.OK);
        }
        return new ResponseEntity<>("Invalid login", HttpStatus.UNAUTHORIZED);
    }

    @PatchMapping("/logout")
    @ResponseBody
    public ResponseEntity<String> logOut(HttpSession httpSession) {
        httpSession.invalidate();
        return new ResponseEntity<>("logout succeed.", HttpStatus.OK);
    }

    @GetMapping("/cards")
    public String cardHome(Model model) {
        return "card";
    }

    @PostMapping(
            value = "/addExcel",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    @ResponseBody
    public ResponseEntity<ResponseDto> addExcel(
            @RequestPart(required = false) MultipartFile cardExcel) throws IOException {
        adminService.insertCard(cardExcel);
        return new ResponseEntity<>(ResponseDto.of(true, StatusCode.OK), HttpStatus.OK);
    }
}
