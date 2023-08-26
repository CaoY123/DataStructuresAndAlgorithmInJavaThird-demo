package com.mine.chapter03.practice;

import com.mine.chapter03.MySingleLinkedList;

import java.util.Iterator;

/**
 * @author CaoY
 * @date 2023-08-26 12:08
 * @description 练习 3.11 假设单链表使用一个头结点实现，但无尾结点，并假设它只保留对该头结点的引用，
 * 编写一个类，包含：
 *  a. 返回链表大小的方法。
 *  b. 打印链表的方法。
 *  c. 测试值 x 是否含于链表的方法。
 *  d. 如果值 x 尚未含于链表，添加值 x 到该链表的方法。
 *  e. 如果值 x 含于链表，将 x 从该链表中删除的方法。
 */
public class Solution03_11 {

    public static void main(String[] args) {
        MySingleLinkedList<Integer> list = new MySingleLinkedList<>();
        for (int i = 0; i < 8; i++) {
            list.add(i + 1);
        }
        System.out.println("单链表尺寸：" + list.size());
        System.out.print("链表元素：");
        list.show();
        System.out.println();
        System.out.println("链表包括 6 吗：" + (list.contains(6) ? "包括" : "不包括"));
        System.out.println("链表包括 9 吗：" + (list.contains(9) ? "包括" : "不包括"));

        list.remove(1);
        list.remove(3);
        list.remove(8);
        list.remove(10);
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
