package com.mine.chapter03.practice;

import com.mine.chapter03.MyQueue;
import com.mine.chapter03.MySingleLinkedListQueue;

/**
 * @author CaoY
 * @date 2023-08-31 19:50
 * @description 练习 3.32 使用单链表高效实现队列类，不用头结点和尾结点
 */
public class Solution03_32 {

    public static void main(String[] args) {
        MyQueue<Integer> queue = new MySingleLinkedListQueue<> ();
        for (int i = 0; i < 10; i++) {
            queue.enQueue(i + 1);
        }
        System.out.println("插入 10 个元素后，队列为空吗：" + (queue.isEmpty() ? "是" : "否"));
        System.out.println("队列的元素个数：" + queue.size());
        System.out.println("队列顶元素为：" + queue.front());
        System.out.print("队列的元素为：");
        while (!queue.isEmpty()) {
            System.out.print(queue.deQueue() + " ");
        }
        System.out.println();

        System.out.println("所有元素 deQueue 后，又插入 5 个元素");
        for (int i = 0; i < 5; i++) {
            queue.enQueue(11 + i);
        }
        System.out.println("队列中元素的个数：" + queue.size());
        queue.clear();
        System.out.println("clear 后，队列中元素的个数：" + queue.size());
        System.out.println("对列为空吗：" + (queue.isEmpty() ? "是" : "否"));
    }
}
