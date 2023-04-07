package com.team.piickle.util.dto;

import lombok.Getter;

@Getter
public class KeyValueDto implements Comparable<KeyValueDto>{
    private final String value;
    private final int count;

    public KeyValueDto(String value, int count) {
        this.value = value;
        this.count = count;
    }

    @Override
    public int compareTo(KeyValueDto o) {
        return this.count - o.count;
    }
}
