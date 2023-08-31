package com.mine.chapter03.practice;

import com.mine.chapter03.MySingleLinkedListStack;

/**
 * @author CaoY
 * @date 2023-08-30 23:01
 * @description 练习 3.31 使用单链表高效实现栈类，不用头结点和尾结点
 */
public class Solution03_31 {

    public static void main(String[] args) {
        MySingleLinkedListStack<Integer> stack = new MySingleLinkedListStack<>();
        for (int i = 0; i < 10; i++) {
            stack.push(i + 1);
        }
        System.out.println("插入 10 个元素后，栈为空吗：" + (stack.isEmpty() ? "是" : "否"));
        System.out.println("栈的元素个数：" + stack.size());
        System.out.println("栈顶元素为：" + stack.top());
        System.out.print("栈的元素为：");
        while (!stack.isEmpty()) {
            System.out.print(stack.pop() + " ");
        }
        System.out.println();

        System.out.println("所有元素 pop 后，又插入 5 个元素");
        for (int i = 0; i < 5; i++) {
            stack.push(11 + i);
        }
        System.out.println("栈中元素的个数：" + stack.size());
        stack.clear();
        System.out.println("clear 后，栈中元素的个数：" + stack.size());
        System.out.println("栈为空吗：" + (stack.isEmpty() ? "是" : "否"));
    }
}
