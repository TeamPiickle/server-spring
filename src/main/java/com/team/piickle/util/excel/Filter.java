package com.team.piickle.util.excel;

import java.util.Arrays;

public enum Filter {
    MALE("남자", 12),
    FEMALE("여자", 13),
    TEENAGER("10대", 14),
    TWENTY("20대", 15),
    THIRTY("30대", 16),
    PERSONAL("개인", 17),
    COUPLE("커플", 18),
    FRIEND("친구", 19),
    ORGANIZATION("단체", 20),
    ADULT("19금", 21),
    NOMATTER("상관없음", 22),
    NEW("새로워요", 23),
    FRIENDLY("친근해요", 24),
    BESTFRIEND("절친해요", 25);

    private final int cellNumber;
    private final String value;

    Filter(String value, int cellNumber) {
        this.value = value;
        this.cellNumber = cellNumber;
    }

    public String value() {
        return value;
    }

    public int cellNumber() {
        return cellNumber;
    }

    public static Filter getValue(int idx) {
        return Arrays.stream(values()).filter(value -> value.cellNumber == idx).findAny().orElse(null);
    }
}
