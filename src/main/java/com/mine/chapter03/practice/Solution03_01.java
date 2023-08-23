package com.mine.chapter03.practice;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/**
 * @author CaoY
 * @date 2023-08-23 22:10
 * @description 习题 3.1
 * 使用迭代器来进行遍历，可以使得 List 不管使用什么实现（数组或链表），都能在一遍遍历的基础上完成输出操作，
 * 其时间复杂度为 O(n)
 */
public class Solution03_01 {

    public static void main(String[] args) {
        List<Integer> L = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            L.add(101 + i);
        }

        List<Integer> P = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            P.add(5 * i + 1);
        }

        System.out.println("表L：" + L);
        System.out.println("表P：" + P);
        printLots(L, P);
    }

    /**
     * 根据 P 中指定的 L 的下标，打印出 L 中的相应的元素
     * 注意：L 和 P 为包含升序排列的整数，且 P 中的元素对应在 L 中的下标都存在
     * @param L 打印元素的表
     * @param P 提供 L 中打印元素的下标的表
     */
    private static void printLots(List<Integer> L, List<Integer> P) {
        ListIterator<Integer> lIter = L.listIterator();
        ListIterator<Integer> pIter = P.listIterator();
        int lastIndex = 0; // 用于辅助标记上次迭代器指向的索引位置
        while (pIter.hasNext()) {
            int index = pIter.next();
            for (int i = lastIndex; i < index - 1; i++) {
                lIter.next();
            }
            System.out.print(lIter.next() + " ");
            lastIndex = index;
        }
        System.out.println();
    }

}
