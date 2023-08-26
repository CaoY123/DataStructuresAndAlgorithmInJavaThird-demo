package com.mine.chapter03.practice;

import com.mine.chapter03.MyLinkedList;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * @author CaoY
 * @date 2023-08-26 11:38
 * @description 练习 3.10 提供对 MyLinkedList removeAll 方法的实现，时间复杂度为 O(M * N)，
 * 其中 M 是 items 的元素个数，N 是 list 的元素个数
 */
public class Solution03_10 {

    public static void main(String[] args) {
        MyLinkedList<Integer> list = new MyLinkedList<>();
        for (int i = 0; i < 10; i++) {
            list.add(i + 1);
        }

        Set<Integer> set = new HashSet<>();
        set.add(0);
        for (int i = 1; i < 10; i += 2) {
            set.add(i);
        }
        set.add(20);

        System.out.print("list：");
        Iterator<Integer> iter = list.iterator();
        while (iter.hasNext()) {
            System.out.print(iter.next() + " ");
        }
        System.out.println();
        System.out.println("set：" + set);
        list.removeAll(set);

        System.out.println("从 list 中删除所有位于 set 的元素：");
        iter = list.iterator();
        while (iter.hasNext()) {
            System.out.print(iter.next() + " ");
        }
        System.out.println();
    }
}
