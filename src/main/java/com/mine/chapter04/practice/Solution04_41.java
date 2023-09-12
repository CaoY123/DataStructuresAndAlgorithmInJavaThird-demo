package com.mine.chapter04.practice;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @author CaoY
 * @date 2023-09-12 20:29
 * @description 练习 4.41
 * 编写一个例程以层序列出二叉树的结点，先列出根，然后列出深度为 1 的那些结点，再列出深度为 2 的结点，等等。
 * 必须要在线性时间内完成这个工作。证明你的时间结界。
 *
 * 回答：就是使用队列，借助其先进先出的特点，实现层序遍历，这个复杂度是 O(N) 的，因为以供要有 N 个元素进出
 * 队，且每个元素均为 1 次。这也符合在线性时间内完成层序遍历的要求。
 */
public class Solution04_41 {

    public static void main(String[] args) {
        BinaryNode<Integer> root = new BinaryNode<>(100);
        root.left = new BinaryNode<>(50);
        root.right = new BinaryNode<>(150);
        root.left.left = new BinaryNode<>(25);
        root.left.right = new BinaryNode<>(75);
        root.right.left = new BinaryNode<>(125);
        root.right.right = new BinaryNode<>(175);

        // BinaryNode<Integer> root = null; // 这个情况也可以测一下
        levelOrder(root);
    }

    private static <AnyType> void levelOrder(BinaryNode<AnyType> t) {
        if (t == null) {
            return;
        }

        Queue<BinaryNode<AnyType> > queue = new ConcurrentLinkedQueue<>();
        queue.add(t);
        while (!queue.isEmpty()) {
            BinaryNode<AnyType> ingNode = queue.poll();
            System.out.print(ingNode.element + " ");
            if (ingNode.left != null) {
                queue.add(ingNode.left);
            }
            if (ingNode.right != null) {
                queue.add(ingNode.right);
            }
        }
        System.out.println();
    }

    private static class BinaryNode<AnyType> {
        public AnyType element;
        public BinaryNode<AnyType> left;
        public BinaryNode<AnyType> right;

        public BinaryNode() {

        }

        public BinaryNode(AnyType element) {
            this(element, null, null);
        }

        public BinaryNode(AnyType element, BinaryNode<AnyType> left, BinaryNode<AnyType> right) {
            this.element = element;
            this.left = left;
            this.right = right;
        }
    }
}
