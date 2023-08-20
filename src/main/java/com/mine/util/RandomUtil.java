package com.mine.util;

import java.util.concurrent.ThreadLocalRandom;

/**
 * @author CaoY
 * @date 2023-08-20 12:57
 * @description 随机数工具类，用于构造一些随机的数据
 */
public class RandomUtil {
    /**
     * 一个简单的使用 ThreadLocalRandom 构造随机整数数组的工具方法
     * @param min   数组的最小值
     * @param max   数组的最大值
     * @param count 数组的元素个数
     * @return      int 型数组
     */
    public static int[] randomIntArrays(int min, int max, int count) {
        return ThreadLocalRandom.current()
                .ints(count, min, max)
                .toArray();
    }
}
