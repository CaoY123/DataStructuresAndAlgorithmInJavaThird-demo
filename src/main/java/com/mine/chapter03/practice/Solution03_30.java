package com.mine.chapter03.practice;

import com.mine.chapter03.SelfAdjustingLinkedList;

/**
 * @author CaoY
 * @date 2023-08-30 20:25
 * @description 练习 3.30
 * a. 写出自调整表的数组实现。在自调整表中，所有的插入都在前端进行，当一个元素被 find
 * 访问时，它就被移到移到表的前端而并不改变其余的相对顺序。
 *
 * b. 写出自调整表的链表实现。
 *
 * c. 是一道证明题，具体看题目...
 *  c 所述是一个很明显的结论，应该可以用归纳法做，在这里不作书写。
 */
public class Solution03_30 {

    public static void main(String[] args) {
//        SelfAdjustingArrayList<Integer> list = new SelfAdjustingArrayList<>();
        SelfAdjustingLinkedList<Integer> list = new SelfAdjustingLinkedList<>();
        for (int i = 0; i < 10; i++) {
            list.add(i + 1);
        }
//        list.reverseShow("倒序打印：");
        list.show("从前到后的所有元素：");

        int res = list.find(3);
        res = list.find(5);
        res = list.find(6);
        res = list.find(1);
        res = list.find(12);
        list.show("查询 5 个不同的数后，从前到后的所有元素：");
        System.out.println("res = " + res);

        list.remove(0);
        list.show("删除第 0 个元素后：");
        list.remove(1);
        list.show("删除第 1 个元素后：");
        list.remove(2);
        list.show("删除第 2 个元素后：");
        list.remove(list.size() - 1);
        list.show("删除 4 个元素后：");
    }
}
