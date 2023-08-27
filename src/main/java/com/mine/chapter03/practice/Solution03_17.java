package com.mine.chapter03.practice;

import com.mine.chapter03.MyArrayList;

import java.util.Iterator;

/**
 * @author CaoY
 * @date 2023-08-27 14:43
 * @description 练习 3.17
 *
 * 注：只对 MyArrayList 的 iterator() 返回的正向迭代器提供了较为严格的迭代器检测
 */
public class Solution03_17 {

    public static void main(String[] args) {
        MyArrayList<Integer> list = new MyArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(i + 1);
        }

        Iterator<Integer> iter = list.iterator();
        System.out.print("正序遍历：");
        while (iter.hasNext()) {
            System.out.print(iter.next() + " ");
        }
        System.out.println();

        iter = list.iterator();
        while (iter.hasNext()) {
            // 删除偶数元素
            if (iter.next() % 2 == 0) {
                iter.remove();
            }
        }

        System.out.print("删除偶数元素后，正序遍历：");
        iter = list.iterator();
        while (iter.hasNext()) {
            System.out.print(iter.next() + " ");
        }
        System.out.println();
    }
}
