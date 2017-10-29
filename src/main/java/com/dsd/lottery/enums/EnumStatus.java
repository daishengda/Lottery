package com.dsd.lottery.enums;

/**
 * 组合同步状态
 * 
 * @author daishengda
 *
 */
public enum EnumStatus {

    /** 就绪，未开始对组合算法拆分 **/
    READY(0),

    /** 正在运行 */
    RUN(1),

    /** 成功 **/
    SUCCESS(2),

    /** 失败 **/
    FAIL(3);

    private int status;

    private EnumStatus(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
