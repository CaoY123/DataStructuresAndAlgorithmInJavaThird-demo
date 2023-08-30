package com.mine.chapter03.practice;

import com.mine.chapter03.MyLinkerListDeque;

/**
 * @author CaoY
 * @date 2023-08-29 22:49
 * @description 练习 3.28 实现一个双端队列
 */
public class Solution03_28 {

    public static void main(String[] args) {
        MyLinkerListDeque<Integer> deque = new MyLinkerListDeque<>();
        deque.push(3);
        deque.push(2);
        deque.push(1);
        deque.inject(4);
        deque.inject(5);
        deque.inject(6);
        // 按上面的流程存放完元素后， deque 里的元素应该是：1,2,3,4,5,6
        // 下面打印的结果应该是：1 2 3 6 5 4
        int count = deque.size();
        for (int i = 0; i < count; i++) {
            if (i < 3) {
                System.out.print(deque.pop() + " ");
            } else {
                System.out.print(deque.eject() + " ");
            }
        }
        System.out.println();
        System.out.println("*************************");

        for (int i = 0; i < 5; i++) {
            deque.push(i + 1);
        }
        System.out.println("添加 5 个元素后，deque 的元素个数：" + deque.size());
        System.out.println("deque 为空吗：" + (deque.isEmpty() ? "空" : "不空"));
        deque.clear();
        System.out.println("clear 后，deque 为空吗：" + (deque.isEmpty() ? "空" : "不空") + "，deque 的元素个数为：" + deque.size());
    }
}
