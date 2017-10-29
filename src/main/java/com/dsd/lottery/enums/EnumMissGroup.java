package com.dsd.lottery.enums;


/**
 * 遗漏算法组合
 * 
 * @author daishengda
 *
 */
public enum EnumMissGroup {

    TWO_GROUP(2),

    THREE_GROUP(3),

    FOUR_GROUP(4),

    FIVE_GROUP(5);

    private int digit;

    private EnumMissGroup(int digit) {
        this.digit = digit;
    }

    public int getDigit() {
        return digit;
    }

    public static EnumMissGroup getEnumMissGroup(int digit) {
        EnumMissGroup[] values = EnumMissGroup.values();
        for (EnumMissGroup enumMissGroup : values) {
            if (enumMissGroup.digit == digit) {
                return enumMissGroup;
            }
        }
        return EnumMissGroup.TWO_GROUP;
    }

    public int[] getMatch() {
        int digitTemp = this.getDigit();
        int index = 2;
        int size = digitTemp - index + 1;
        int[] match = new int[size];
        int i = 0;
        for (; index <= digitTemp; index++) {
            match[i++] = index;
        }
        return match;
    }
}
