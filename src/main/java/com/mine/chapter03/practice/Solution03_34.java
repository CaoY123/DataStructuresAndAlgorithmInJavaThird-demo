package com.mine.chapter03.practice;

/**
 * @author CaoY
 * @date 2023-08-31 20:41
 * @description 练习 3.34
 * 如果从某个结点 p 开始，接着跟有足够数目的 next 链将把我们带回到结点 p，那么这个链表包含一个循环，
 * p 不必是该表的第一个结点，给你一个链表，它包含 N 个结点，不过 N 的值是不知道的，要求用 O(1) 的空间
 * 复杂度解决这个问题。
 *
 * 快慢指针策略：
 *  使用两个移动速度不同的指针，快指针每次移动两步，慢指针每次移动一步；如果存在环，那么快慢指针终会相遇，
 *  即指向同一个结点，否则，快指针会比慢指针更早到达终点，终点一般为 null，问题得以解决。
 */
public class Solution03_34 {

    public static void main(String[] args) {
        Node<Integer> head1 = new Node<>();
        head1.next = new Node<>(1);
        head1.next.next = new Node<>(2);
        head1.next.next.next = new Node<>(3);
        head1.next.next.next.next = new Node<>(4);
        head1.next.next.next.next.next = new Node<>(5);
        System.out.println("head1 是否存在环结构：" + (hasLoop(head1) ? "是" : "否"));

        Node<Integer> head2 = null;
        System.out.println("head2 是否存在环结构：" + (hasLoop(head2) ? "是" : "否"));

        Node<Integer> head3 = new Node<>();
        System.out.println("head3 是否存在环结构：" + (hasLoop(head3) ? "是" : "否"));

        Node<Integer> head4 = new Node<>();
        head4.next = new Node<>(1);
        System.out.println("head4 是否存在环结构：" + (hasLoop(head4) ? "是" : "否"));

        Node<Integer> head5 = new Node<>();
        head5.next = new Node<>(1);
        head5.next = head5;
        System.out.println("head5 是否存在环结构：" + (hasLoop(head5) ? "是" : "否"));

        Node<Integer> head6 = new Node<>();
        head6.next = new Node<>(1);
        head6.next.next = head6.next;
        System.out.println("head6 是否存在环结构：" + (hasLoop(head6) ? "是" : "否"));

        Node<Integer> head7 = new Node<>();
        head7.next = new Node<>(1);
        head7.next.next = new Node<>(2);
        head7.next.next.next = new Node<>(3);
        head7.next.next.next.next = new Node<>(4);
        head7.next.next.next.next.next = new Node<>(5);
        head1.next.next.next.next.next.next = head7.next;
        System.out.println("head7 是否存在环结构：" + (hasLoop(head7) ? "是" : "否"));
    }

    /**
     * 判断单链表中是否存在环结构，如果传入的 head 为 null，则返回 false
     * @param head      单链表的头结点
     * @param <AnyType> 任意类型
     * @return          如果存在环结构，则返回 true；反之，则返回 false
     */
    private static <AnyType> boolean hasLoop(Node<AnyType> head) {
        if (head == null || head.next == null) {
            return false;
        }

        // 设置 快慢指针出发点不同，不然的话第一个 while 就进不去了，这会导致错误结果
        Node<AnyType> fast = head.next;
        Node<AnyType> slow = head;

        while (fast != null && fast != slow) {
            fast = fast.next;
            if (fast != null) {
                fast = fast.next;
            } else {
                break;
            }
            slow = slow.next;
        }

        return fast != null;
    }

    private static class Node<AnyType> {
        public AnyType data;
        public Node<AnyType> next;

        public Node() {
        }

        public Node(AnyType data) {
            this.data = data;
        }
    }
}
