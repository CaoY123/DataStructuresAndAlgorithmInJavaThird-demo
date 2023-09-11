package com.mine.chapter04.practice;

/**
 * @author CaoY
 * @date 2023-09-11 22:10
 * @description 练习 4.36
 *
 * 回答：所谓理想的平衡二叉树，实际上就是一棵满二叉平衡树，我们需要观察这棵树的特征，然后构建。
 * 其实思想与练习 4.35 一致，只不过这次的高度要求左右子树是一致的（因为要满二叉树），所以对于
 * 高度的递减也是一致的。具体理解可以参考 Solution04_35。不过答案还提供了一种思路，就是类似
 * 于练习 4.34，就是用二分法代替原来的随机数生成依次确定结点，这是基于这个满二叉查找树的性质，
 * 这么做也可以，这里就不实现了。
 *
 * 注：该方法的运行时间是 O(2 ^ (h + 1) - 1)
 */
public class Solution04_36 {

    public static void main(String[] args) {
        BinaryNode<Integer> root = genFullAVLTree(0);
        System.out.println(root.element);
    }

    private static BinaryNode<Integer> genFullAVLTree(int h) {
        return genAVLTreeNode(h, new int[]{0});
    }

    private static BinaryNode<Integer> genAVLTreeNode(int h, int[] lastNode) {
        BinaryNode<Integer> t = null;
        if (h >= 0) {
            t = new BinaryNode<>();
            t.left = genAVLTreeNode(h - 1, lastNode);
            t.element = ++lastNode[0];
            t.right = genAVLTreeNode(h - 1, lastNode);
        }

        return t;
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
