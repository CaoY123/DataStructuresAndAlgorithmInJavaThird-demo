package com.mine.chapter03;

import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * @author CaoY
 * @date 2023-08-30 21:22
 * @description 自适应表链表实现（回答练习 3.30 b）
 */
public class SelfAdjustingLinkedList<AnyType> {

    private Node<AnyType> head;
    private Node<AnyType> tail;
    private int theSize;

    public SelfAdjustingLinkedList() {
        head = new Node<>(null, null, null);
        tail = new Node<>(null, head, null);
        head.next = tail;
        theSize = 0;
    }

    /**
     * 向表前端添加指定元素
     * @param x 指定添加的元素
     * @return  在不抛出异常的情况下，返回 true
     */
    public boolean add(AnyType x) {
        Node<AnyType> currentNode = new Node<>(x, head, head.next);
        head.next.prev = currentNode;
        head.next = currentNode;
        theSize++;
        return true;
    }

    /**
     * 按照实行下标删除相应的元素
     * @param index 指定要删除元素的下标，0 < index < size()
     * @return  被删除的元素
     * 如果表为空，则抛出 NoSuchElementException；
     * 如果 index 下标不在要求的范围内，则抛出 IndexOutOfBoundsException
     */
    public AnyType remove(int index) {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException();
        }

        int i = -1;
        Node<AnyType> removeNode = null;
        if (index <= size() / 2) {
            // 从前往后遍历
            removeNode = head;
            while (i < index) {
                removeNode = removeNode.next;
                i++;
            }
        } else {
            removeNode = tail;
            while (size() - 1 - i > index) {
                removeNode = removeNode.prev;
                i++;
            }
        }

        Node<AnyType> prevNode = removeNode.prev;
        prevNode.next = removeNode.next;
        removeNode.next.prev = prevNode;
        removeNode.prev = null;
        removeNode.next = null;
        theSize--;

        return removeNode.data;
    }

    /**
     * 在表中寻找指定值的下标
     * @param x 待寻找下标的指定值
     * @return  如果找到，则返回指定值的下标（0 - size()），否则返回 -1
     * 若表为空，则抛出 NoSuchElementException
     */
    public int find(AnyType x) {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        int res = 0;
        Node<AnyType> currentNode = head.next;
        while (currentNode != tail && !Objects.equals(x, currentNode.data)) {
            res++;
            currentNode = currentNode.next;
        }

        if (res == size()) {
            res = -1;
        } else {
            Node<AnyType> prevNode = currentNode.prev;
            prevNode.next = currentNode.next;
            currentNode.next.prev = prevNode;
            currentNode.next = head.next;
            head.next.prev = currentNode;
            head.next = currentNode;
            currentNode.prev = head;
        }

        return res;
    }

    /**
     * 求表中元素的个数
     * @return  表中元素的个数
     */
    public int size() {
        return theSize;
    }

    /**
     * 判断表是否为空
     * @return  表为空，返回 true；反之，返回 false
     */
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * 从前到后遍历元素（这里所谓的前，就是下标从大到小，这是基于这样做插入方便而设计的）
     * @param description   对于打印的链表的描述
     */
    public void show(String description) {
        System.out.print(description);
        Node<AnyType> currentNode = head.next;
        while (currentNode != tail) {
            System.out.print(currentNode.data + " ");
            currentNode = currentNode.next;
        }
        System.out.println();
    }

    /**
     * 倒序打印，为了 debug 所写
     * @param description   对于打印的链表的描述
     */
    public void reverseShow(String description) {
        System.out.print(description);
        Node<AnyType> currentNode = tail.prev;
        while (currentNode != head) {
            System.out.print(currentNode.data + " ");
            currentNode = currentNode.prev;
        }
        System.out.println();
    }

    private class Node<AnyType> {
        public AnyType data;
        public Node<AnyType> prev;
        public Node<AnyType> next;

        public Node() {

        }

        public Node(AnyType data) {
            this.data = data;
        }

        public Node(AnyType data, Node<AnyType> prev, Node<AnyType> next) {
            this.data = data;
            this.prev = prev;
            this.next = next;
        }
    }
}
