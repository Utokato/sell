package com.ml.sell.enums;

/**
 * 枚举类接口
 *
 * @author
 */
public interface CodeEnum {
    /**
     * 顶层接口，实现该接口的枚举类都应该能获得返回码
     * @return 返回状态码
     */
    Integer getCode();
}
