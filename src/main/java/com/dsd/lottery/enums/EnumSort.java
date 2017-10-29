package com.dsd.lottery.enums;

/**
 * ˳��
 * 
 * @author daishengda
 *
 */
public enum EnumSort {

    /**
     * ����
     */
    ASC_SORT("asc"),

    /**
     * ����
     */
    DESC_SORT("desc");

    private String sort;

    private EnumSort(String sort) {
        this.sort = sort;
    }

    public String getSort() {
        return sort;
    }
}
