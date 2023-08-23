package com.mine.chapter03.practice;

/**
 * @author CaoY
 * @date 2023-08-23 23:10
 * @description 练习 3.2
 *
 * 注：下面的代码没有对于一些数据有效性的校验，笔者认为此处这不是重点，假设题中的量都符合正常运行的条件，
 * 我们主要关心的是其引用变化的过程。
 */
public class Solution03_02 {

    public static void main(String[] args) {
        // 此处不设计测试用例了，看懂过程即可，可以辅助画图以帮助理解
    }

    // 我们假设这里的单双链表都有头结点和尾结点，单链表的结点的结构如下：
    private class SingleLinkedNode<AnyType> {
        public AnyType data;
        public SingleLinkedNode<AnyType> next;
    }

    // beforeNode 为要互换位置的两个相邻结点中靠前结点的前置结点
    private static <AnyType> void swapNode(SingleLinkedNode<AnyType> beforeNode) {
        SingleLinkedNode<AnyType> node = beforeNode.next; // 要交换的位于前面位置的结点
        SingleLinkedNode<AnyType> afterNode = node.next; // 要交换的位于后面位置的结点

        beforeNode.next = afterNode;
        node.next = afterNode.next; // 这一句必须在 rearNode.next = prevNode; 这句之前，否则会出现丢失数据的情况
        afterNode.next = node;
    }

    // 双链表结点结构
    private class LinkedNode<AnyType> {
        public AnyType data;
        public LinkedNode<AnyType> prev;
        public LinkedNode<AnyType> next;
    }

    private static <AnyType> void swapNode(LinkedNode<AnyType> node) {
        LinkedNode<AnyType> beforeNode = node.prev;
        LinkedNode<AnyType> afterNode = node.next;

        node.next = afterNode.next;
        afterNode.next = node;
        node.prev = afterNode;
        node.next.prev = node; // 这个容易漏掉，最好画出图来分析
        afterNode.prev = beforeNode;
        beforeNode.next = afterNode;
    }
}
