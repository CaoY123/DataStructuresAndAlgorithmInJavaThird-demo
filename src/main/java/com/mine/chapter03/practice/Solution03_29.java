package com.mine.chapter03.practice;

import com.mine.chapter03.MySingleLinkedList;

import java.util.Iterator;

/**
 * @author CaoY
 * @date 2023-08-30 19:55
 * @description 练习 3.29 单链表的反转（题目中说的是打印双列表的倒序，这太简单了，因为有尾结点，而且又有
 * 指向前驱的引用，这么做显然没有什么意思）。
 *
 * 因此笔者将题目更改为单链表的逆置，而且没有尾结点，只有头结点
 */
public class Solution03_29 {

    public static void main(String[] args) {
        MySingleLinkedList<Integer> list = new MySingleLinkedList<>();
        int count = 3; // count 等于 0,1,2,3...分别测试 list 中不同元素个数的效果
        for (int i = 0; i < count; i++) {
            list.add(i + 1);
        }

        Iterator<Integer> iter = list.iterator();
        System.out.print("链表逆置前：");
        while (iter.hasNext()) {
            System.out.print(iter.next() + " ");
        }
        System.out.println();

        list.reverseList();
        System.out.print("链表逆置后：");
        iter = list.iterator();
        while (iter.hasNext()) {
            System.out.print(iter.next() + " ");
        }
        System.out.println();
    }


}
