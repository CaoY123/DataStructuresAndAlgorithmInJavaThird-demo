package com.mine.chapter04.practice;

/**
 * @author CaoY
 * @date 2023-09-10 21:43
 * @description 练习 4.32
 * 设计一个递归的线性算法，该算法测试一棵二叉树是否在每一个结点都满足查找树的有序性质。
 *
 * 回答：还是递归的策略，先判断这个结点符不符合要求，不符合要求直接返回 false；符合要求的话
 * 进而判断其左右子树是不是也符合要求，都符合要求才能返回 true，否则，返回 false。需要注意
 * 的是，为了防止局部成立但整体不成立的情况，比如：某个结点的右子树的其中一个结点的左儿子不仅
 * 比其父结点小，而且比这个右子树的根结点还要小。所以笔者意识到每个子树的结点都是有界的，当然
 * 类似根结点这样的结点其上下界为 null，或者其中一个界为 null，需要我们做好这方面的控制。而
 * compareRes2、compareRes3 所表达的就是这个意思。
 */
public class Solution04_32 {

    public static void main(String[] args) {
        System.out.println("仅提供算法代码，不作测试了。");
        // 调用下面方法的时候，需要传入 isTrueSearchTree(root, null, null)
    }

    private static <AnyType extends Comparable<? super AnyType> > boolean
    isTrueSearchTree(BinaryNode<AnyType> t, AnyType min, AnyType max) {
        if (t == null) {
            return true;
        }

        if (t.left != null) {
            int compareRes1 = t.left.element.compareTo(t.element);
            int compareRes2 = min != null ? t.left.element.compareTo(min) : 1;
            int compareRes3 = max != null ? t.left.element.compareTo(max) : -1;
            if (compareRes1 >= 0 || compareRes2 <= 0 || compareRes3 >= 0) {
                return false;
            }
        }

        if (t.right != null) {
            int compareRes1 = t.right.element.compareTo(t.element);
            int compareRes2 = min != null ? t.right.element.compareTo(min) : 1;
            int compareRes3 = max != null ? t.right.element.compareTo(max) : -1;
            if (compareRes1 >= 0 || compareRes2 <= 0 || compareRes3 >= 0) {
                return false;
            }
        }

        return isTrueSearchTree(t.left, min, t.element) == false ?
                false : isTrueSearchTree(t.right, t.element, max);
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
