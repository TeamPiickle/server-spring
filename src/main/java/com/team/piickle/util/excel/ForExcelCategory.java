package com.team.piickle.util.excel;

import java.util.Arrays;

public enum ForExcelCategory {
    COUPLE("커플", 4),
    FRIEND("친구", 5),
    SOPT("SOPT", 6),
    BLINDDATE("소개팅", 7),
    BALANCEGAME("밸런스게임", 8),
    IF("IF", 9),
    SERIOUSCONVERSATION("진지한 대화", 10),
    ICEBREAKING("아이스브레이킹", 11);

    private final int cellNumber;
    private final String value;

    ForExcelCategory(String value, int cellNumber) {
        this.value = value;
        this.cellNumber = cellNumber;
    }

    public String value() {
        return value;
    }

    public int cellNumber() {
        return cellNumber;
    }

    public static ForExcelCategory getValue(int idx) {
        return Arrays.stream(values()).filter(value -> value.cellNumber == idx).findAny().orElse(null);
    }
}
