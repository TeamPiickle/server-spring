package com.team.piickle.admin.dto;

import lombok.Getter;

@Getter
public class BookmarkedCardResponseDto implements Comparable<BookmarkedCardResponseDto> {

    private final String content;
    private final int count;

    public BookmarkedCardResponseDto(String content, int count) {
        this.content = content;
        this.count = count;
    }

    @Override
    public int compareTo(BookmarkedCardResponseDto o) {
        return o.count - this.count;
    }
}
