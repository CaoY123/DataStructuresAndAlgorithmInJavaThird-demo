package com.mine.chapter04.practice;

import java.util.Random;

/**
 * @author CaoY
 * @date 2023-09-10 23:25
 * @description 练习 4.35
 * 写出具有生成最少结点、高度为 h 的 AVL 树的方法，该方法的运行时间是多少？
 *
 * 注：
 *  1. 答案是正确的，而且要完全按照答案的写法写，绝对不能将 lastNode 数组替换为一个 int 或 Integer 的变量。
 *  因为一旦将其替换为 int 或 Integer 的变量，就会使得上一轮递归对于结点值的变化不会传递到当前的递归中，而
 *  我们是需要这种传递的。因为本质上我们构造的这棵 AVL 树就是采用中序遍历的方式，这会使得结点的元素从小到大的
 *  被组织起来，所以我们需要这个元素值的传递。而为了使 AVL 树的结点最少，就是使得每一个有左右子树的结点的右子
 *  树都比左子树的层数多一即可，这就是为什么对高度要做那样的处理。
 *  2. 这个题值得好好研究，很容易踩 Java 值传递的坑。
 */
public class Solution04_35 {

    public static void main(String[] args) {
        BinaryNode<Integer> root = genAVLTree(3);
        System.out.println(root.element);
    }

    private static BinaryNode<Integer> genAVLTreeNode(int height, int[] lastNode) {
        BinaryNode<Integer> t = null;
        if (height >= 0) {
            t = new BinaryNode<>();
            t.left = genAVLTreeNode(height - 2, lastNode);
            t.element = ++lastNode[0];
            t.right = genAVLTreeNode(height - 1, lastNode);
        }

        return t;
    }

    private static BinaryNode<Integer> genAVLTree(int height) {
        return genAVLTreeNode(height, new int[]{height});
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
