package com.mine.chapter04;

import java.util.Comparator;
import java.util.NoSuchElementException;

/**
 * @author CaoY
 * @date 2023-09-04 19:35
 * @description 二叉查找树（抄自书 P78 - P86）
 */
public class BinarySearchTree<AnyType extends Comparable<? super AnyType> > {

    private BinaryNode<AnyType> root;
    private Comparator<? super AnyType> cmp;

    public BinarySearchTree() {
        this.root = null;
    }

    public BinarySearchTree(Comparator<? super AnyType> cmp) {
        root = null;
        this.cmp = cmp;
    }

    /**
     * 将该二叉查找树置空
     */
    public void makeEmpty() {
        root = null;
    }

    /**
     * 判断该二叉查找树是否为空
     * @return  若该二叉查找树为空，返回 true；反之，则为 false
     */
    public boolean isEmpty() {
        return root == null;
    }

    /**
     * 该二叉查找树是否包含指定值
     * @param x 指定直
     * @return  若包含指定值，返回 true，反之为 false
     */
    public boolean contains(AnyType x) {
        return contains(x, root);
    }

    /**
     * 找到该二叉查找树中最小的元素
     * @return  最小的元素
     * 如果树为空，则抛出 NoSuchElementException
     */
    public AnyType findMin() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return findMin(root).element;
    }

    /**
     * 找到该二叉查找树中最大的元素
     * @return  最大的元素
     * 如果树为空，则抛出 NoSuchElementException
     */
    public AnyType findMax() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return findMax(root).element;
    }

    public void insert(AnyType x) {
        root = insert(x, root);
    }

    public void remove(AnyType x) {
        root = remove(x, root);
    }

    public void printTree() {
        // todo
    }

    /**
     * 输出指定描述并按中序遍历打印树中的元素
     * @param description   指定描述
     */
    public void inorderShow(String description) {
        System.out.print(description);
        inorderShow(root);
        System.out.println();
    }

    private int myCompare(AnyType a, AnyType b) {
        if (cmp != null) {
            return cmp.compare(a, b);
        } else {
            return a.compareTo(b);
        }
    }

    private boolean contains(AnyType x, BinaryNode<AnyType> t) {
        if (t == null) {
            return false;
        }

        int compareRes = myCompare(x, t.element);
        if (compareRes == 0) {
            return true;
        } else if (compareRes < 0) {
            return contains(x, t.left);
        } else {
            return contains(x, t.right);
        }
    }

    private BinaryNode<AnyType> findMin(BinaryNode<AnyType> t) {
        if (t == null) {
            return null;
        }

        BinaryNode<AnyType> minNode = findMin(t.left);

        return minNode == null ? t : minNode;
    }

    private BinaryNode<AnyType> findMax(BinaryNode<AnyType> t) {
        if (t == null) {
            return null;
        }

        BinaryNode<AnyType> maxNode = findMax(t.right);

        return maxNode == null ? t : maxNode;
    }

    private BinaryNode<AnyType> insert(AnyType x, BinaryNode<AnyType> t) {
        if (t == null) {
            t = new BinaryNode<>(x);
            return t;
        }

        int compareRes = myCompare(x, t.element);
        if (compareRes == 0) {
            // 什么也不做
        } else if (compareRes < 0) {
            t.left = insert(x, t.left);
        } else {
            t.right =  insert(x, t.right);
        }

        return t;
    }

    private BinaryNode<AnyType> remove(AnyType x, BinaryNode<AnyType> t) {
        if (t == null) {
            return t;
        }

        int compareRes = myCompare(x, t.element);
        if (compareRes < 0) {
            t.left = remove(x, t.left);
        } else if(compareRes > 0) {
            t.right = remove(x, t.right);
        } else if (t.left != null && t.right != null) {
            // 这里设计的太巧妙了，值得反复品鉴
            t.element = findMin(t).element;
            t.right = remove(t.element, t.right);
        } else {
            t = (t.left != null) ? t.left : t.right;
        }

        return t;
    }

    private void printTree(BinaryNode<AnyType> t) {
        // todo

    }

    private void inorderShow(BinaryNode<AnyType> t) {
        if (isEmpty()) {
            System.out.print("这是一个空的查找二叉树");
        }
        if (t == null) {
            return;
        }

        inorderShow(t.left);
        System.out.print(t.element + " ");
        inorderShow(t.right);
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

    public static void main(String[] args) {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        tree.insert(9);
        tree.insert(8);
        tree.insert(11);
        tree.insert(6);
        tree.insert(10);
        tree.inorderShow("插入 5 个元素后：");

        System.out.println("树为空吗：" + (tree.isEmpty() ? "空" : "不空"));
        System.out.println("包含 5 吗：" + (tree.contains(5) ? "包含" : "不包含"));
        System.out.println("包含 10 吗：" + (tree.contains(10) ? "包含" : "不包含"));
        System.out.println("当前树中的最大值：" + tree.findMax());
        System.out.println("当前树中的最小值：" + tree.findMin());

        tree.remove(10);
        tree.inorderShow("删除 10 后：");
        tree.remove(5);
        tree.inorderShow("删除 5 后：");

        tree.makeEmpty();
        System.out.println("调用 makeEmpty 后，树为空吗：" + (tree.isEmpty() ? "空" : "不空"));
        tree.inorderShow("调用 makeEmpty 后，");
    }
}
