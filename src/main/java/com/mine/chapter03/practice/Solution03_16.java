package com.mine.chapter03.practice;

import com.mine.chapter03.MyArrayList;

import java.util.Iterator;

/**
 * @author CaoY
 * @date 2023-08-27 11:55
 * @description 练习 3.16 MyArrayList 的反向迭代器测试
 */
public class Solution03_16 {

    public static void main(String[] args) {
        MyArrayList<Integer> list = new MyArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(i + 1);
        }

        Iterator<Integer> rIter = list.reverseIterator();
        System.out.print("逆序遍历：");
        while (rIter.hasNext()) {
            System.out.print(rIter.next() + " ");
        }
        System.out.println();

        rIter = list.reverseIterator();
        while (rIter.hasNext()) {
            // 删除偶数元素
            if (rIter.next() % 2 == 0) {
                rIter.remove();
            }
        }

        System.out.print("删除偶数元素后，逆序遍历：");
        rIter = list.reverseIterator();
        while (rIter.hasNext()) {
            System.out.print(rIter.next() + " ");
        }
        System.out.println();
    }
}
