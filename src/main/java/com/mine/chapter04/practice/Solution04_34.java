package com.mine.chapter04.practice;

import java.util.Random;

/**
 * @author CaoY
 * @date 2023-09-10 22:59
 * @description 练习 4.34
 * 写出一棵随机生成 N-结点二叉查找树的方法，该树具有从 1 到 N 的不同的关键字。你编写的例程的运行时间是多少？
 *
 * 回答：答案那个方法很巧妙，属于我想不到的类型，我想着是使用一个数组，存储前面已经插入到二叉查找树的元素，
 * 然后每次生成不在数组里面的元素，最后按生成的顺序将元素放入到二叉查找树中，但是答案的方式更巧妙。
 *
 * 注：答案的方法很值得学习，有必要好好看看代码！
 */
public class Solution04_34 {

    public static void main(String[] args) {
        BinaryNode<Integer> root = generateRandomBinarySearchTree(20);
        System.out.println(root.element);
    }

    private static BinaryNode<Integer> generateRandomBinarySearchTree(int n) {
        return makeNode(1, n);
    }

    // 很巧妙，这样做很高效，而且不用额外的空间存储已经生成的元素，不错，值得学习！
    private static BinaryNode<Integer> makeNode(int lower, int upper) {
        BinaryNode<Integer> t = null;
        int randomValue = 0;
        if (lower <= upper) {
            t = new BinaryNode<>(randomValue = randomInt(lower, upper),
                    makeNode(lower, randomValue - 1),
                    makeNode(randomValue + 1, upper));
        }

        return t;
    }

    private static Random random = new Random(System.currentTimeMillis());
    private static int randomInt(int lower, int upper) {
        return random.nextInt(upper - lower + 1) + lower;
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
