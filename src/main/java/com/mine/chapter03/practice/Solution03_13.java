package com.mine.chapter03.practice;

import com.mine.chapter03.MyArrayList;

import java.util.ListIterator;

/**
 * @author CaoY
 * @date 2023-08-26 23:18
 * @description 练习 3.13 添加 ListIterator 对 MyArrayList 类的支持
 */
public class Solution03_13 {

    public static void main(String[] args) {
        MyArrayList<Integer> list = new MyArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(i + 1);
        }

        ListIterator<Integer> iter = list.listIterator();
        System.out.print("正序遍历：");
        while (iter.hasNext()) {
            System.out.print(iter.next() + " ");
        }
        System.out.println();
        System.out.println("目前迭代器的上一个下标：" + iter.previousIndex());
        System.out.println("目前迭代器的下一个下标：" + iter.nextIndex());
        System.out.println("**********************");

        System.out.print("逆序遍历：");
        while (iter.hasPrevious()) {
            System.out.print(iter.previous() + " ");
        }
        System.out.println();
        System.out.println("目前迭代器的上一个下标：" + iter.previousIndex());
        System.out.println("目前迭代器的下一个下标：" + iter.nextIndex());
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
            iter.set(iter.next() + 1);
        }
        System.out.print("剩余的每个元素 +1 后，逆序遍历表：");
        while (iter.hasPrevious()) {
            System.out.print(iter.previous() + " ");
        }
        System.out.println();
        System.out.println("**********************");

        iter.add(16);
        for (int i = 0; i < 3; i++) {
            if (iter.hasNext()) {
                iter.next();
            }
        }
        iter.add(0);
        while (iter.hasNext()) {
            iter.next();
        }
        iter.add(-20);
        System.out.print("添加新元素后，逆序遍历表：");
        while (iter.hasPrevious()) {
            System.out.print(iter.previous() + " ");
        }
        System.out.println();
        System.out.println("**********************");

        while (iter.hasNext()) {
            iter.next();
        }
        while (iter.hasPrevious()) {
            if (iter.previous() % 2 == 0) {
                iter.remove();
            }
        }
        System.out.print("删除偶数后，正序遍历表：");
        while (iter.hasNext()) {
            System.out.print(iter.next() + " ");
        }
        System.out.println();
        System.out.println("**********************");
    }
}
