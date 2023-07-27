package com.team.piickle.util.calculation;

public class Calculation {
    protected Calculation() {}

    public static int getRatio(int totalCount, int partCount) {
        if (totalCount == 0) {
            return 0;
        }
        return (int) Math.floor(partCount * 100 / totalCount);
    }
}
