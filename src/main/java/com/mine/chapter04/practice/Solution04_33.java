package com.mine.chapter04.practice;

/**
 * @author CaoY
 * @date 2023-09-10 22:47
 * @description 练习 4.33
 * 编写一个递归方法，该方法使用对树 T 的根结点的引用而返回从树 T 删除所有树叶所得到的树的根结点的引用。
 */
public class Solution04_33 {

    public static void main(String[] args) {
        System.out.println("简单的程序，也好理解。");
    }

    private static <AnyType extends Comparable<? super AnyType> > BinaryNode<AnyType>
    removeAllLeaves(BinaryNode<AnyType> t) {
        if (t == null) {
            return t;
        }

        if (t.left == null && t.right == null) {
            // 就是要删除的叶子结点
            t = null;
        } else {
            t.left = removeAllLeaves(t.left);
            t.right = removeAllLeaves(t.right);
        }

        return t;
    }

    private static class BinaryNode<AnyType> {
        public AnyType element;
        public BinaryNode<AnyType> left;
        public BinaryNode<AnyType> right;

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
