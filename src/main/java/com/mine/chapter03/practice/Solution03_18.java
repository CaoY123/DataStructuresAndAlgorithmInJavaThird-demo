package com.mine.chapter03.practice;

import com.mine.chapter03.MyLinkedList;

/**
 * @author CaoY
 * @date 2023-08-27 14:54
 * @description
 */
public class Solution03_18 {

    public static void main(String[] args) {
        MyLinkedList<Integer> list = new MyLinkedList<>();
        list.addLast(2);
        list.addFirst(1);
        list.addFirst(0);
        list.addLast(3);

        list.show();
        System.out.println();

        list.removeFirst();
        System.out.print("removeFirst 后：");
        list.show();
        System.out.println();

        list.removeLast();
        System.out.print("removeLast 后：");
        list.show();
        System.out.println();

        System.out.println("第一个元素：" + list.getFirst());
        System.out.println("第二个元素：" + list.getLast());
    }
}
