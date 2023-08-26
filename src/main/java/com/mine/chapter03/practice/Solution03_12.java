package com.mine.chapter03.practice;

import com.mine.chapter03.MySortedSingleLinkedList;

import java.util.Iterator;

/**
 * @author CaoY
 * @date 2023-08-26 18:59
 * @description 练习 3.12
 */
public class Solution03_12 {

    public static void main(String[] args) {
        MySortedSingleLinkedList<Integer> list = new MySortedSingleLinkedList<>();
        list.add(6);
        list.add(5);
        list.add(23);
        list.add(14);
        list.add(8);
        list.add(0);

        System.out.print("可排序的单链表的元素为：");
        list.show();
        System.out.println();
        System.out.println("链表包括 6 吗：" + (list.contains(6) ? "包括" : "不包括"));
        System.out.println("链表包括 9 吗：" + (list.contains(9) ? "包括" : "不包括"));

        list.remove(5);
        list.remove(12);
        System.out.print("删除元素后的链表：");
        list.show();
        System.out.println();
        System.out.println("*****************************");
        System.out.print("使用迭代器遍历：");
        Iterator<Integer> iter = list.iterator();
        while (iter.hasNext()) {
            System.out.print(iter.next() + " ");
        }
        System.out.println();
        // 使用迭代器清除偶数元素
        iter = list.iterator();
        while (iter.hasNext()) {
            int val = iter.next();
            if (val % 2 == 0) {
                iter.remove();
            }
        }
        System.out.print("删除偶数元素后的链表：");
        list.show();
    }
}
