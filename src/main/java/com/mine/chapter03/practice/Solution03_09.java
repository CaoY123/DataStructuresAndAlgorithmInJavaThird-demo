package com.mine.chapter03.practice;

import com.mine.chapter03.MyArrayList;

import java.util.*;

/**
 * @author CaoY
 * @date 2023-08-26 10:55
 * @description 练习 3.9 实现 MyArrayList 类的 addAll 方法，时间复杂度为 O(N)
 */
public class Solution03_09 {

    public static void main(String[] args) {
        MyArrayList<Integer> list = new MyArrayList<>();
        list.add(0);
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < 8; i++) {
            set.add(i + 1);
        }
        list.addAll(set);
        Iterator<Integer> iter = list.iterator();
        while (iter.hasNext()) {
            System.out.print(iter.next() + " ");
        }
        System.out.println();
    }
}
