package com.mine.chapter03;

import java.util.NoSuchElementException;

/**
 * @author CaoY
 * @date 2023-08-22 23:56
 * @description 使用自定义的 MyLinkedList 实现的队列
 */
public class MyLinkedListQueue<AnyType> implements MyQueue<AnyType> {

    private MyLinkedList list;

    public MyLinkedListQueue() {
        list = new MyLinkedList();
    }

    @Override
    public boolean enQueue(AnyType x) {
        list.add(x);
        return true;
    }

    @Override
    public AnyType deQueue() {
        AnyType frontVal = front();
        list.remove(0);
        return frontVal;
    }

    @Override
    public AnyType front() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        AnyType frontVal = (AnyType) list.get(0);
        return frontVal;
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public void clear() {
        list.clear();
    }

    public static void main(String[] args) {
        MyQueue<Integer> queue = new MyLinkedListQueue<>();
        queue.enQueue(1);
        queue.enQueue(2);
        queue.enQueue(3);
        queue.enQueue(4);
        queue.enQueue(5);

        System.out.println("队首的元素：" + queue.front());
        System.out.println("元素个数：" + queue.size());
        System.out.print("依次出队所有元素：");
        while (!queue.isEmpty()) {
            System.out.print(queue.deQueue() + " ");
        }
        System.out.println();

        queue.enQueue(6);
        queue.enQueue(7);
        queue.enQueue(8);
        System.out.println("重新 enQueue 后，队列空吗：" + (queue.isEmpty() ? "空" : "不空"));
        queue.clear();
        System.out.println("调用 clear() 后，队列空吗：" + (queue.isEmpty() ? "空" : "不空"));
    }
}
