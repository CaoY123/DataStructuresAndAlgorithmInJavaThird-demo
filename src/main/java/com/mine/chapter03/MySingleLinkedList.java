package com.mine.chapter03;

import java.util.Iterator;
import java.util.Objects;

/**
 * @author CaoY
 * @date 2023-08-26 12:09
 * @description 自定义的单链表（回答练习 3.11）
 *
 * 注：下面的单链表没有考虑线程安全
 */
public class MySingleLinkedList<AnyType> implements Iterable<AnyType>{

    private Node<AnyType> head; // 头结点，不存放元素，其 next 引用指向存放第一个元素的结点
    private int theSize;

    public MySingleLinkedList() {
        head = new Node<>();
        theSize = 0;
    }

    public int size() {
        return theSize;
    }

    public void show() {
        Node<AnyType> node = head.next;
        while (node != null) {
            System.out.print(node.data + " ");
            node = node.next;
        }
    }

    public boolean contains(AnyType x) {
        Node<AnyType> node = head.next;
        while (node != null) {
            if (Objects.equals(x, node.data)) {
                return true;
            }
            node = node.next;
        }
        return false;
    }

    public void add(AnyType x) {
        Node<AnyType> node = head;
        while (node.next != null) {
            node = node.next;
        }

        Node<AnyType> newNode = new Node<>(x);
        node.next = newNode;
        theSize++;
    }

    public boolean remove(AnyType x) {
        Node<AnyType> node = head.next;
        Node<AnyType> preNode = head;
        while (node != null) {
            if (Objects.equals(x, node.data)) {
                preNode.next = node.next;
                theSize--;
                return true;
            }

            preNode = node;
            node = node.next;
        }
        return false;
    }

    @Override
    public Iterator<AnyType> iterator() {
        return new SingleListIterator<>();
    }

    private class SingleListIterator<Anytype> implements Iterator<AnyType> {

        private Node<AnyType> preCur;

        public SingleListIterator() {
            this.preCur = head;
        }

        @Override
        public boolean hasNext() {
            return preCur.next != null;
        }

        @Override
        public AnyType next() {
            preCur = preCur.next;
            return preCur.data;
        }

        @Override
        public void remove() {
            MySingleLinkedList.this.remove(preCur.data);
        }
    }

    private class Node<AnyType> {
        public AnyType data;
        public Node<AnyType> next;

        public Node() {

        }

        public Node(AnyType data) {
            this.data = data;
        }

        public Node(AnyType data, Node<AnyType> next) {
            this.data = data;
            this.next = next;
        }
    }
}
