package com.mine.chapter03;

import java.util.NoSuchElementException;

/**
 * @author CaoY
 * @date 2023-08-31 19:51
 * @description 不使用头结点的单链表实现队列
 */
public class MySingleLinkedListQueue<AnyType> implements MyQueue<AnyType>{

    private Node<AnyType> front;
    private Node<AnyType> rear;
    private int theSize;

    public MySingleLinkedListQueue() {
        front = null;
        rear = null;
        theSize = 0;
    }

    @Override
    public boolean enQueue(AnyType x) {
        if (isEmpty()) {
            front = new Node<>(x, rear);
            rear = front;
        } else {
            rear.next = new Node<>(x, null);
            rear = rear.next;
        }
        theSize++;
        return true;
    }

    @Override
    public AnyType deQueue() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        AnyType res = front.data;
        Node<AnyType> node = front;
        front = front.next;
        node.next = null;
        theSize--;
        return res;
    }

    @Override
    public AnyType front() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        return front.data;
    }

    @Override
    public int size() {
        return theSize;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public void clear() {
        front = null;
        rear = null;
        theSize = 0;
    }

    private class Node<AnyType> {
        public AnyType data;
        public Node<AnyType> next;

        public Node(AnyType data, Node<AnyType> next) {
            this.data = data;
            this.next = next;
        }
    }
}
