package com.mine.chapter04.practice;

/**
 * @author CaoY
 * @date 2023-09-10 21:28
 * @description 练习 4.31
 * 编写一些高效率的方法，只使用对二叉树的根的引用 T，并计算：
 *  a. T 中结点的个数。
 *  b. T 中树叶的个数。
 *  c. T 中满结点的个数。
 *
 * 注：上面的问题都是采用递归的思想解决即可。
 */
public class Solution04_31 {

    public static void main(String[] args) {
        System.out.println("看懂算法即可，不作测试了。");
    }

    // a. 计算结点的个数，当然也可以在相应的数据结构中增加一个 count 变量来标记，可以用于在常数时间内获取
    // 不过要记得 insert 和 remove 时对其进行相应的增减操作。
    private static <AnyType> int getNodeCount(BinaryNode<AnyType> t) {
        if (t == null) {
            return 0;
        }

        return getNodeCount(t.left) + getNodeCount(t.right) + 1;
    }

    // b. 获取二叉树中叶子结点的个数
    private static <AnyType> int getLeavesCount(BinaryNode<AnyType> t) {
        if (t == null) {
            return 0;
        }
        if (t.left == null && t.right == null) {
            return 1;
        }

        return getLeavesCount(t.left) + getLeavesCount(t.right);
    }

    // c. 计算二叉树中满结点的个数
    private static <AnyType> int getFullNodeCount(BinaryNode<AnyType> t) {
        if (t == null) {
            return 0;
        }
        int count = 0;
        if (t.left != null && t.right != null) {
             count = 1;
        }

        return getFullNodeCount(t.left) + getFullNodeCount(t.right) + count;
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
