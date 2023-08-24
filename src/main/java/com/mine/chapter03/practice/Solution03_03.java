package com.mine.chapter03.practice;

import com.mine.chapter03.MyLinkedList;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author CaoY
 * @date 2023-08-24 19:45
 * @description 练习 3.3 实现 MyLinkedList 的 contains 例程
 *
 * 答案里没有考虑传入是 null 值的情况，不过笔者认为应当考虑。
 */
public class Solution03_03 {

    public static void main(String[] args) {
        MyLinkedList<Integer> list1 = new MyLinkedList<>();
        for (int i = 0; i < 10; i++) {
            list1.add(i + 1);
        }
        list1.add(null);

        System.out.print("list 中的元素：");
        Iterator<Integer> iter1 = list1.iterator();
        while (iter1.hasNext()) {
            System.out.print(iter1.next() + " ");
        }
        System.out.println();
        System.out.println("是否包含 3：" + (list1.contains(3) ? "是" : "否"));
        System.out.println("是否包含 100：" + (list1.contains(100) ? "是" : "否"));
        System.out.println("是否包含 null：" + (list1.contains(null) ? "是" : "否"));
        list1.remove(list1.size() - 1);
        System.out.println("删除 null 值后，是否包含 null：" + (list1.contains(null) ? "是" : "否"));

        List<Integer> list = new ArrayList<>();
        list.add(3);
        list.add(2);
        list.add(1);
        list.add(null);
        System.out.println(list);
        list.sort(Integer::compareTo);
        System.out.println(list);
    }
}
