package com.team.piickle.user.service;

import com.team.piickle.bookmark.domain.Bookmark;
import com.team.piickle.bookmark.repository.BookmarkRepository;
import com.team.piickle.card.domain.Card;
import com.team.piickle.card.repository.CardRepository;
import com.team.piickle.common.exception.GeneralException;
import com.team.piickle.user.domain.GenderStatus;
import com.team.piickle.user.domain.User;
import com.team.piickle.user.dto.UserBookmarkedResponseDto;
import com.team.piickle.user.dto.UserProfileResponseDto;
import com.team.piickle.user.dto.UserSignupRequestDto;
import com.team.piickle.user.repository.UserRepository;
import com.team.piickle.util.StatusCode;
import com.team.piickle.util.s3.S3Upload;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.context.MessageSource;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final CardRepository cardRepository;
    private final BookmarkRepository bookmarkRepository;
    private final PasswordEncoder passwordEncoder;
    private final MessageSource messageSource;
    private final S3Upload s3Upload;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info(username);
        User user =
                userRepository
                        .findByEmail(username)
                        .orElseThrow(
                                () ->
                                        new GeneralException(
                                                messageSource.getMessage(
                                                        "USER.VIEW.BY.EMAIL.FAIL", null, Locale.getDefault())));
        if (user == null) {
            log.error("User not found in the database");
            throw new GeneralException("User not found in the database");
        } else {
            log.info("User found in the database: {}", username);
        }
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(), user.getHashedPassword(), authorities);
    }

    @Transactional
    public void signup(UserSignupRequestDto userSignupRequest, MultipartFile profileImage)
            throws IOException {
        if (userRepository.existsByEmail(userSignupRequest.getEmail())) {
            throw new GeneralException(
                    StatusCode.VALIDATION_ERROR,
                    messageSource.getMessage("ALREADY.EXIST.EMAIL", null, Locale.getDefault()));
        }
        String profileImageUrl = "";
        //        if (profileImage != null) {
        //            profileImageUrl = s3Upload.upload(profileImage);
        //        }
        log.info(userSignupRequest.getEmail());
        userRepository.save(
                User.builder()
                        .email(userSignupRequest.getEmail())
                        .hashedPassword(passwordEncoder.encode(userSignupRequest.getPassword()))
                        .nickname(userSignupRequest.getNickname())
                        .birthday(userSignupRequest.getBirthday())
                        .gender(GenderStatus.MALE.getStatus())
                        .profileImageUrl(profileImageUrl)
                        .build());
    }

    @Transactional
    public UserProfileResponseDto getUser(String userEmail) {
        User user =
                userRepository
                        .findByEmail(userEmail)
                        .orElseThrow(
                                () ->
                                        new GeneralException(
                                                messageSource.getMessage(
                                                        "USER.VIEW.BY.EMAIL.FAIL", null, Locale.getDefault())));
        return UserProfileResponseDto.builder()
                // .name(user.getName())
                .nickname(user.getNickname())
                .email(user.getEmail())
                .profileImageUrl(user.getProfileImageUrl())
                .build();
    }

    @Transactional
    public void updateProfileImage(String userId, MultipartFile profileImage) throws IOException {
        String profileImageUrl = "";
        //        if (profileImage != null) {
        //            profileImageUrl = s3Upload.upload(profileImage);
        //        }
        User user =
                userRepository
                        .findByEmail(userId)
                        .orElseThrow(
                                () ->
                                        new GeneralException(
                                                messageSource.getMessage(
                                                        "USER.VIEW.BY.EMAIL.FAIL", null, Locale.getDefault())));
        User profileImageUrlChangedUser =
                User.builder()
                        .profileImageUrl(profileImageUrl)
                        .gender(user.getGender())
                        .hashedPassword(user.getHashedPassword())
                        .email(user.getEmail())
                        // .bookmarks(user.getBookmarks())
                        .nickname(user.getNickname())
                        // .name(user.getName())
                        .build();
        user.update(profileImageUrlChangedUser);
    }

    @Transactional
    public void nicknameDuplicationCheck(String nickname) {
        userRepository
                .findByNickname(nickname)
                .orElseThrow(
                        () ->
                                new GeneralException(
                                        messageSource.getMessage("ALREADY.EXIST.NICKNAME", null, Locale.getDefault())));
    }

    @Transactional
    public void deleteUser(String userId) {}

    @Transactional
    public void updateNickname(String userEmail, String nickname) {
        User user =
                userRepository
                        .findByEmail(userEmail)
                        .orElseThrow(
                                () ->
                                        new GeneralException(
                                                messageSource.getMessage(
                                                        "USER.VIEW.BY.EMAIL.FAIL", null, Locale.getDefault())));
        User nicknameChangedUser =
                User.builder()
                        .profileImageUrl(user.getProfileImageUrl())
                        .gender(user.getGender())
                        .hashedPassword(user.getHashedPassword())
                        .email(user.getEmail())
                        .nickname(nickname)
                        .name(user.getName())
                        .build();
        user.update(nicknameChangedUser);
        userRepository.save(user);
    }

    @Transactional
    public String changeBookmark(String userEmail, String cardId) {
        User user =
                userRepository
                        .findByEmail(userEmail)
                        .orElseThrow(
                                () ->
                                        new GeneralException(
                                                messageSource.getMessage(
                                                        "USER.VIEW.BY.EMAIL.FAIL", null, Locale.getDefault())));
        Card card =
                cardRepository
                        .findById(cardId)
                        .orElseThrow(
                                () ->
                                        new GeneralException(
                                                messageSource.getMessage("READ.CARD.FAIL", null, Locale.getDefault())));

        if (bookmarkRepository.findByUserAndCard(new ObjectId(user.getId()), new ObjectId(cardId)).isEmpty()) {
            bookmarkRepository.save(Bookmark.builder()
                    .user(user.getId())
                    .card(card.getId())
                    .build());
            user.getCardIdList().add(card.getId());
            userRepository.save(user);
            return cardId;
        }
        user.getCardIdList().remove(card.getId());
        bookmarkRepository.delete(bookmarkRepository.findByUserAndCard(new ObjectId(user.getId()), new ObjectId(cardId)).get());
        return cardId;
    }

    @Transactional
    public void updatePassword(String userEmail, String password) {
        User user =
                userRepository
                        .findByEmail(userEmail)
                        .orElseThrow(
                                () ->
                                        new GeneralException(
                                                messageSource.getMessage(
                                                        "USER.VIEW.BY.EMAIL.FAIL", null, Locale.getDefault())));
        String hashedPassword = passwordEncoder.encode(password);
        User nicknameChangedUser =
                User.builder()
                        .profileImageUrl(user.getProfileImageUrl())
                        .gender(user.getGender())
                        .hashedPassword(hashedPassword)
                        .email(user.getEmail())
                        .nickname(user.getNickname())
                        .name(user.getName())
                        .build();
        user.update(nicknameChangedUser);
        userRepository.save(user);
    }
    @Transactional
    public List<UserBookmarkedResponseDto> getBookmarks(String userEmail) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new GeneralException("존재하지 않는 유저 입니다."));
        List<Card> bookmarkedCard = cardRepository.findAllByIdIn(user.getCardIdList());
        return bookmarkedCard.stream()
                .map(card -> new UserBookmarkedResponseDto(card))
                .collect(Collectors.toList());
    }
}
