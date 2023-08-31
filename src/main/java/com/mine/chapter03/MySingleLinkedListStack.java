package com.mine.chapter03;

import java.util.EmptyStackException;

/**
 * @author CaoY
 * @date 2023-08-31 19:27
 * @description 不使用头结点的单链表实现栈
 */
public class MySingleLinkedListStack<AnyType> implements Stack<AnyType> {

    private Node<AnyType> topNode; // 指向栈顶元素
    private int theSize;

    public MySingleLinkedListStack() {
        topNode = null;
        theSize = 0;
    }

    /**
     * 向栈顶插入指定元素
     * @param x 指定插入的元素
     * @return  在不抛出异常的情况下，返回 true
     */
    @Override
    public boolean push(AnyType x) {
        Node<AnyType> node = new Node<>(x, topNode);
        topNode = node;
        theSize++;
        return true;
    }

    /**
     * 弹出栈顶元素
     * @return  栈顶元素
     * 若栈为空，抛出 EmptyStackException
     */
    @Override
    public AnyType pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        AnyType res = topNode.data;
        Node<AnyType> node = topNode;
        topNode = topNode.next;
        node.next = null;
        theSize--;

        return res;
    }

    /**
     * 返回栈顶元素
     * @return  栈顶元素
     * 若栈为空，抛出 EmptyStackException
     */
    @Override
    public AnyType top() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }

        return topNode.data;
    }

    /**
     * 栈中的元素个数
     * @return  栈中的元素个数
     */
    @Override
    public int size() {
        return theSize;
    }

    /**
     * 判断栈是否为空
     * @return  若栈为空，返回 true；反之，返回 false
     */
    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * 清空栈
     */
    @Override
    public void clear() {
        theSize = 0;
        topNode = null;
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
