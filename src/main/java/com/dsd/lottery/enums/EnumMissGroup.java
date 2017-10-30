package com.dsd.lottery.enums;


/**
 * 遗漏算法组合
 * 
 * @author daishengda
 *
 */
public enum EnumMissGroup {

    TWO_GROUP(2) {
        @Override
        public int[] getMatch() {
            //3是二星组三的情况，23至少出一次就算
            return new int[]{2,3};
        }
    },

    THREE_GROUP(3){
        @Override
        public int[] getMatch() {
            //6是三星组6的情况，234至少出一次就算
            return new int[]{2,3,6};
        }
    },

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
