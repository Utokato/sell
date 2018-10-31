package com.ml.sell.utils;

import java.util.Random;

/**
 * 主键生成工具类
 *
 * @author
 */
public class KeyUtils {

    /**
     * 生成唯一主键
     * 格式：时间 + 随机数
     * 可以使用雪花算法来生成
     *
     * @return
     */
    public static synchronized String genUniqueKey() {
        Random random = new Random();

        Integer num = random.nextInt(900000) + 100000;

        return System.currentTimeMillis() + String.valueOf(num);

    }

}
