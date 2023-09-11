package com.mine.chapter04.practice;

/**
 * @author CaoY
 * @date 2023-09-11 22:27
 * @description 练习 4.37
 *
 * 回答：还是递归思想，这里不作测试，也不分析它的时间复杂度
 */
public class Solution04_37 {

    public static void main(String[] args) {
        System.out.println("不对下列代码进行测试");
    }

    private static <AnyType extends Comparable<? super AnyType> >
    void printElements(AnyType k1, AnyType k2, BinaryNode<AnyType> t) {
        if (t == null) {
            return;
        }

        int cmpRes1 = k1.compareTo(t.element);
        int cmpRes2 = k2.compareTo(t.element);
        if (cmpRes1 > 0) {
            printElements(k1, k2, t.right);
        } else if (cmpRes2 < 0) {
            printElements(k1, k2, t.left);
        } else {
            System.out.println(t.element + " ");
            printElements(k1, k2, t.left);
            printElements(k1, k2, t.right);
        }
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
