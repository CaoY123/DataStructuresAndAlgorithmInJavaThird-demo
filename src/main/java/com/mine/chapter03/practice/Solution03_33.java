package com.mine.chapter03.practice;

import com.mine.chapter03.MyArrayListQueue;
import com.mine.chapter03.MyQueue;

/**
 * @author CaoY
 * @date 2023-08-31 20:13
 * @description 练习 3.33 使用循环数组高效实现队列类
 *
 * MyArrayListQueue 是使用循环数组实现队列的类
 *
 * 注：下面的代码专门测试当队列中初始的默认数组空间放满元素后（9 个元素，还有一个位置不放元素），
 * 先出队3个元素再入队3个元素，目的就是为了观察当队尾在数组中的下标位于队头在数组中的下标之 前
 * 时集合的状态，不过需要 debug 进去看效果（就是在中间代码的 3 个 enQueue 和 deQueue之间
 * debug）。
 */
public class Solution03_33 {

    public static void main(String[] args) {
        MyQueue<Integer> queue = new MyArrayListQueue<>();
        for (int i = 0; i < 9; i++) {
            queue.enQueue(i + 1);
        }

        System.out.println("插入 9 个元素后，队列的元素个数：" + queue.size());
        queue.deQueue();
        queue.deQueue();
        queue.deQueue();
        queue.enQueue(11);
        queue.enQueue(12);
        queue.enQueue(13);

        System.out.println("出队 3 个元素再入队 3 个元素后");
        System.out.println("队列的元素个数：" + queue.size());
        System.out.print("队列的元素：");
        while (!queue.isEmpty()) {
            System.out.print(queue.deQueue() + " ");
        }
        System.out.println();
    }
}
