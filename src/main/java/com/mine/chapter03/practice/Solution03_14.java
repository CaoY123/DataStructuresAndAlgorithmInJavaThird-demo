package com.mine.chapter03.practice;

import com.mine.chapter03.MyLinkedList;

import java.util.ListIterator;

/**
 * @author CaoY
 * @date 2023-08-27 0:37
 * @description 练习 3.14 添加 ListIterator 对 MyLinkedList 类的支持
 *
 * 比较杂乱，可能还有没有测到的地方，如有错误，恳请指正，谢谢。
 */
public class Solution03_14 {

    public static void main(String[] args) {
        MyLinkedList<Integer> list = new MyLinkedList<>();
        for (int i = 0; i < 10; i++) {
            list.add(i + 1);
        }

        ListIterator<Integer> iter = list.listIterator();
        System.out.print("正序遍历：");
        while (iter.hasNext()) {
            System.out.print(iter.next() + " ");
        }
        System.out.println();
        System.out.println("**********************");

        System.out.print("逆序遍历：");
        while (iter.hasPrevious()) {
            System.out.print(iter.previous() + " ");
        }
        System.out.println();
        System.out.println("**********************");

        while (iter.hasNext()) {
            if (iter.next() % 2 != 0) {
                iter.remove();
            }
        }
        System.out.print("删除奇数后，逆序遍历表：");
        while (iter.hasPrevious()) {
            System.out.print(iter.previous() + " ");
        }
        System.out.println();
        System.out.println("**********************");

        while (iter.hasNext()) {
            int val = iter.next();
            iter.set(val + 1);
        }
        while (iter.hasPrevious()) {
            int val = iter.previous();
            iter.set(val + 1);
        }
        System.out.print("剩余的每个元素两次 +1 后，正序遍历表：");
        while (iter.hasNext()) {
            System.out.print(iter.next() + " ");
        }
        System.out.println();
        System.out.println("**********************");

        iter.add(100);
        int i = iter.previous();
        iter.add(30);
        while (iter.hasPrevious()) {
            iter.previous();
        }
        iter.add(-3);
        System.out.print("添加几个元素后：");
//        iter.previous();
        iter.next();
        iter.add(-5);
        iter.previous();
        iter.previous();
        while (iter.hasNext()) {
            System.out.print(iter.next() + " ");
        }
        System.out.println();
        System.out.println("**********************");
    }
}
