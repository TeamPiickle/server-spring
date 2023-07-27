package com.team.piickle.admin.service;

import com.team.piickle.admin.controller.AdminLoginDto;
import com.team.piickle.admin.dto.BookmarkedCardResponseDto;
import com.team.piickle.auth.admin.AdminUserProperties;
import com.team.piickle.bookmark.domain.Bookmark;
import com.team.piickle.bookmark.repository.BookmarkRepository;
import com.team.piickle.card.domain.Card;
import com.team.piickle.card.repository.CardRepository;
import com.team.piickle.category.domain.Category;
import com.team.piickle.category.repository.CategoryRepository;
import com.team.piickle.common.exception.GeneralException;
import com.team.piickle.user.domain.User;
import com.team.piickle.user.dto.UserProfileResponseDto;
import com.team.piickle.user.repository.UserRepository;
import com.team.piickle.util.dto.KeyValueDto;
import com.team.piickle.util.excel.Filter;
import com.team.piickle.util.excel.ForExcelCategory;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RequiredArgsConstructor
@Service
public class AdminService {

    private final AdminUserProperties adminUserProperties;

    private final UserRepository userRepository;

    private final BookmarkRepository bookmarkRepository;

    private final CardRepository cardRepository;

    private final CategoryRepository categoryRepository;

    @Transactional
    public void insertCard(MultipartFile cardExcel) throws IOException {
        if (cardExcel.isEmpty()) {
            throw new GeneralException();
        }
        String extension = FilenameUtils.getExtension(cardExcel.getOriginalFilename());
        if (!extension.equals("xlsx") && !extension.equals("xls")) {
            throw new GeneralException();
        }
        Workbook workbook = null;
        if (extension.equals("xlsx")) {
            workbook = new XSSFWorkbook(cardExcel.getInputStream());
        } else if (extension.equals("xls")) {
            workbook = new HSSFWorkbook(cardExcel.getInputStream());
        }
        Sheet sheetAt = workbook.getSheetAt(0);
        int physicalNumberOfRows = sheetAt.getPhysicalNumberOfRows();
        log.info(String.valueOf(physicalNumberOfRows));

        for (int i = 1; i < sheetAt.getPhysicalNumberOfRows(); i++) {
            Row row = sheetAt.getRow(i);
            if (Optional.ofNullable(row.getCell(3)).isEmpty()) {
                continue;
            }
            Cell cell = row.getCell(3); // 주제
            String content = cell.toString();
            if (content.equals("")) {
                break;
            }
            List<String> categoryList = new ArrayList<>();
            for (int j = 4; j <= 11; j++) {
                final int index = j;
                Optional.ofNullable(row.getCell(index))
                        .ifPresent(
                                category -> {
                                    if (row.getCell(index).toString().equals("1.0")) {
                                        categoryList.add(ForExcelCategory.getValue(index).value());
                                    }
                                });
            } // 카테고리

            List<Category> categories = categoryRepository.findAllByTitleIn(categoryList);

            List<String> filterList = new ArrayList<>();
            List<String> getCategoryIds =
                    categories.stream().map(category -> category.getId()).collect(Collectors.toList());
            for (int j = 12; j <= 25; j++) {
                final int index = j;
                Optional.ofNullable(row.getCell(index))
                        .ifPresent(
                                f -> {
                                    if (f.toString().equals("1.0")) {
                                        filterList.add(Filter.getValue(index).value());
                                    }
                                });
            } // 필터

            filterList.add("태그");
            String[] split = row.getCell(32).toString().split(",");
            List<String> tagList = Arrays.stream(split).map(s -> s.trim()).collect(Collectors.toList());
            Card card =
                    Card.builder()
                            .content(content)
                            .tags(tagList)
                            .category(getCategoryIds)
                            .filter(filterList)
                            .createdAt(LocalDateTime.now())
                            .updatedAt(LocalDateTime.now())
                            .build();
            cardRepository.save(card);
        }
    }

    @Transactional
    public List<UserProfileResponseDto> getSignUpUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(user -> UserProfileResponseDto.toDto(user))
                .collect(Collectors.toList());
    }

    public int countAllUser() {
        return userRepository.findAll().size();
    }

    @Transactional
    public List<BookmarkedCardResponseDto> getBookmarkedCards() {
        List<Bookmark> bookmarks = bookmarkRepository.findAll();
        HashMap<String, Integer> hashMap = new HashMap<>();
        for (Bookmark bookmark : bookmarks) {
            String key = String.valueOf(bookmark.getCard());
            if (hashMap.containsKey(key)) {
                hashMap.replace(key, hashMap.get(key) + 1);
            } else {
                hashMap.put(key, 1);
            }
        }
        Set<String> keySet = hashMap.keySet();
        List<KeyValueDto> keyValueDtoList = new ArrayList<>();
        for (String key : keySet) {
            keyValueDtoList.add(new KeyValueDto(key, hashMap.get(key)));
        }
        List<String> idList =
                keyValueDtoList.stream()
                        .map(keyValueDto -> keyValueDto.getValue())
                        .collect(Collectors.toList());
        List<Card> cardList = cardRepository.findAllByIdIn(idList);
        List<BookmarkedCardResponseDto> bookmarkedCardResponseDtoList =
                cardList.stream()
                        .map(
                                card -> new BookmarkedCardResponseDto(card.getContent(), hashMap.get(card.getId())))
                        .collect(Collectors.toList());
        Collections.sort(bookmarkedCardResponseDtoList);
        return bookmarkedCardResponseDtoList;
    }

    public boolean login(AdminLoginDto adminLoginDto) {
        return adminUserProperties.isMatched(adminLoginDto.getId(), adminLoginDto.getPassword());
    }
}
