package com.mine.chapter03.practice;

import com.mine.chapter03.MyLinkedList;

import java.util.Iterator;

/**
 * @author CaoY
 * @date 2023-08-27 1:53
 * @description 练习 3.15 将 splice 操作添加到 MyLinkedList 类中
 *
 * 我感觉这个题设计的不好，之前的方法设计就不兼容这个题。为了达到题目中说的常数时间内完成，我必须在
 * MyLinkedList 的内部类 LinkedListIterator 中增加一个 getCurrentNode() 方法来获取当前的结点，
 * 毫无疑问，我得通过一个类型转换才能实现新增方法的可见，这降低了通用性，之前的设计里就没有考虑过这种情况，
 * 所以我觉得这个题设计的不好。而且无语的是英文版答案里也没有提供相应的代码。
 */
public class Solution03_15 {

    public static void main(String[] args) {
        MyLinkedList<Integer> list1 = new MyLinkedList<>();
        for (int i = 0; i < 10; i++) {
            list1.add(i + 1);
        }
        System.out.print("待添加元素的链表：");
        list1.show();
        System.out.println();

        MyLinkedList<Integer> delList = new MyLinkedList<>();
        for (int i = 0; i < 5; i++) {
            delList.add(i + 11);
        }
        System.out.print("待清空的链表：");
        delList.show();
        System.out.println();

        Iterator iter1 = list1.iterator();
        // 经过测试，在 list1 的开始、中间、结尾均可以成功进行 splice 操作，具体可自行通过开通注释
        // 以及进行 next 迭代测试。
//        while (iter1.hasNext()) {
//            iter1.next();
//        }
        list1.splice(iter1, delList);
        System.out.println("splice 操作后");
        System.out.print("待添加元素的链表：");
        list1.show();
        System.out.println();
        System.out.println("待清空的链表里的元素个数：" + delList.size());
        delList.show();
//        System.out.println();
    }
}
