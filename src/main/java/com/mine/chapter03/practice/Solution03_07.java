package com.mine.chapter03.practice;

import java.util.ArrayList;
import java.util.List;

/**
 * @author CaoY
 * @date 2023-08-26 10:08
 * @description 练习 3.7 下面的 makeList 方法的运行时间是多少？
 */
public class Solution03_07 {
    public static void main(String[] args) {
        // 这里的时间复杂度是 O(n^2)，因为添加元素和 精简空间操作可能需要重新拷贝所有元素到新空间
        // 又考虑到外面还有一层循环，所以则是 O(n^2)
    }

    private static List<Integer> makeList(int N) {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            list.add(i);
            list.trimToSize();
        }

        return list;
    }
}
