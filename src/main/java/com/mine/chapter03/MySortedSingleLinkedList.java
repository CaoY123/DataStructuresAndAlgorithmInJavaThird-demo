package com.mine.chapter03;

import java.util.Iterator;
import java.util.Objects;

/**
 * @author CaoY
 * @date 2023-08-26 19:00
 * @description 完成排序的自定义单链表，使得元素在添加的时候能够按规定的比较规则升序排列
 *
 * 因为是单链表，所以也没有办法用二分法提高查找和删除指定元素的效率，
 * 所以只需要在 MySingleLinkedList 的基础上变更 add 方法即可。
 */
public class MySortedSingleLinkedList<AnyType extends Comparable<? super AnyType>> implements Iterable<AnyType> {

    private Node<AnyType> head; // 头结点，不存放元素，其 next 引用指向存放第一个元素的结点
    private int theSize;

    public MySortedSingleLinkedList() {
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
        if (x == null) {
            throw new IllegalStateException();
        }
        Node<AnyType> node = head.next;
        Node<AnyType> preNode = head;
        Node<AnyType> newNode = new Node<>(x);
        while (node != null) {
            if ((preNode == head || x.compareTo(preNode.data) >= 0) &&
                    x.compareTo(node.data) < 0) {
                break;
            }
            preNode = node;
            node = node.next;
        }
        newNode.next = node;
        preNode.next = newNode;
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
            MySortedSingleLinkedList.this.remove(preCur.data);
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
