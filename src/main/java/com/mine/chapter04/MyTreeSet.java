package com.mine.chapter04;

import java.util.*;

/**
 * @author CaoY
 * @date 2023-09-09 9:17
 * @description 照抄实现自定义的 TreeSet 类（参考自练习 4.11 的答案，不完全是抄，有些细节也做了改进）
 *
 * 回答练习 4.11 TreeSet 的二叉查找树版本的实现
 */
public class MyTreeSet<AnyType extends Comparable<? super AnyType> >
        implements Iterable<AnyType> {

    private BinaryNode<AnyType> root;
    private int modCount; // 修改次数
    private Comparator<? super AnyType> cmp; // 自定义的比较器
    private int size;

    public MyTreeSet() {
        root = null;
        modCount = 0;
        size = 0;
    }

    public MyTreeSet(Comparator<? super AnyType> cmp) {
        this();
        this.cmp = cmp;
    }

    /**
     * 将该 TreeSet 置空
     */
    public void makeEmpty() {
        root = null;
        modCount++;
        size = 0;
    }

    /**
     * 得到 TreeSet 中的元素个数
     * @return  TreeSet 中的元素个数
     * 为解决练习 4.11
     */
    public int size() {
        return this.size;
    }

    /**
     * 判断该 TreeSet 是否为空
     * @return  若为空，则返回 true；反之，则返回 false
     */
    public boolean isEmpty() {
        return root == null;
    }

    /**
     * 该 TreeSet 中是否是否包含指定直
     * @param x 指定的待查找的值
     * @return  若包含指定值，则返回 true；反之，返回 false，如果传入的值为 null，也返回 false
     */
    public boolean contains(AnyType x) {
        if (x == null) {
            return false;
        }

        return contains(x, root);
    }

    /**
     * 查找该 TreeSet 中的最小值
     * @return  该 TreeSet 中的最小值
     * @throws NoSuchElementException   若该二叉树为空，则抛出这个异常
     */
    public AnyType findMin() throws NoSuchElementException{
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return findMin(root).element;
    }

    /**
     * 查找该 TreeSet 中的最大值
     * @return  该 TreeSet 中的最大值
     * @throws NoSuchElementException   若该二叉树为空，则抛出这个异常
     */
    public AnyType findMax() throws NoSuchElementException{
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return findMax(root).element;
    }

    /**
     * 向该 TreeSet 中插入指定直
     * @param x 待插入的指定直
     * 注：不能插入 null 值，否则会抛出 IllegalArgumentException
     */
    public void insert(AnyType x) {
        if (x == null) {
            throw new IllegalArgumentException("实参为 null");
        }

        root = insert(x, root, null);
    }

    // remove 需要好好测一测
    public void remove(AnyType x) {
        if (x == null) {
            return;
        }

        root = remove(x, root);
    }

    /**
     * 输出指定描述并按前序遍历打印树中的元素
     * @param description   对于打印树的描述
     */
    public void preorderShow(String description) {
        System.out.print(description);
        if (isEmpty()) {
            System.out.print("这是一个空的查找二叉树");
        } else {
            preorderShow(root);
        }
        System.out.println();
    }

    /**
     * 输出指定描述并按中序遍历打印树中的元素
     * @param description   指定描述
     */
    public void inorderShow(String description) {
        System.out.print(description);
        if (isEmpty()) {
            System.out.print("这是一个空的查找二叉树");
        } else {
            inorderShow(root);
        }
        System.out.println();
    }

    /**
     * 输出指定描述并按后序遍历打印树中的元素
     * @param description   指定描述
     */
    public void postorderShow(String description) {
        System.out.print(description);
        if (isEmpty()) {
            System.out.print("这是一个空的查找二叉树");
        } else {
            postorderShow(root);
        }
        System.out.println();
    }

    /**
     * 根据指定描述依次采用三种遍历方式打印 TreeSet
     * @param description   指定描述
     */
    public void allOrderShow(String description) {
        System.out.println(description);
        preorderShow("前序遍历打印：");
        inorderShow("中序遍历打印：");
        postorderShow("后序遍历打印：");
        System.out.println("***************************************");
    }

    /**
     * 根据指定值获取相应结点
     * @param x 指定直
     * @return  返回的结点的一个拷贝
     * 为了完成练习 4.12 添加的工具方法，并不建议调用
     */
     BinaryNode<AnyType> getNode(AnyType x) {
        if (x == null) {
            return null;
        }

        BinaryNode<AnyType> node = getNode(x, root);
        return node == null ? node : new BinaryNode<>(node.element);
     }

    // 这里的 a、b 若为 null 值对于一棵可以排序的树来说也没有实际意义，因此不考虑 null 的情况。
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
        if (compareRes < 0) {
            return contains(x, t.left);
        } else if (compareRes > 0) {
            return contains(x, t.right);
        } else {
            return true;
        }
    }

    private BinaryNode<AnyType> findMin(BinaryNode<AnyType> t) {
        // 这里一开始就可以保证传过来的当前结点不为 null，而且也可以在当前这轮中保证
        // 如果我们关心的子结点为 null，递归可以及时终止。
        return t.left == null ? t : findMin(t.left);
    }

    private BinaryNode<AnyType> findMax(BinaryNode<AnyType> t) {

        return t.right == null ? t : findMax(t.right);
    }

    // 这里需要再传入一个指向 parent 的引用
    private BinaryNode<AnyType> insert(AnyType x, BinaryNode<AnyType> t,
                                       BinaryNode<AnyType> pt) {
        if (t == null) {
            modCount++;
            size++;
            t = new BinaryNode<>(x, null, null, pt);
            return t;
        }

        int compareRes = myCompare(x, t.element);
        if (compareRes < 0) {
            t.left = insert(x, t.left, t);
        } else if (compareRes > 0) {
            t.right = insert(x, t.right, t);
        } else {
            // 什么也不做，TreeSet 中不能插入相同的值
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
        } else if (compareRes > 0) {
            t.right = remove(x, t.right);
        } else if (t.left != null && t.right != null) {
            t.element = findMin(t.right).element;
            t.right = remove(t.element, t.right);
        } else {
            modCount++;
            size--;
            BinaryNode<AnyType> oneChild = (t.left != null) ? t.left : t.right;
            if (oneChild != null) {
                oneChild.parent = t.parent;
            }
//            t.parent = null;
            t = oneChild;
        }

        return t;
    }

    private BinaryNode<AnyType> getNode(AnyType x, BinaryNode<AnyType> t) {
        if (t == null) {
            return null;
        }

        int compareRes = myCompare(x, t.element);
        if (compareRes < 0) {
            return getNode(x, t.left);
        } else if (compareRes > 0) {
            return getNode(x, t.right);
        } else {
            return t;
        }
    }

    @Override
    public Iterator<AnyType> iterator() {
        return new MyTreeSetIterator<>();
    }

    private class MyTreeSetIterator<Anytype> implements Iterator<AnyType> {

        private BinaryNode<AnyType> current; // 指向当前结点
        private BinaryNode<AnyType> previous; // 指向前驱结点
        private int expectedCount; // 期望的修改次数
        private boolean okToRemove; // 是否可以删除的标志
        private boolean atEnd; // 是否指向最后一个元素

        public MyTreeSetIterator() {
            if (root == null) {
                current = root;
                atEnd = true;
            } else {
                current = findMin(root);
                atEnd = false;
            }

            previous = null;
            expectedCount = modCount;
            okToRemove = false;
        }

        @Override
        public boolean hasNext() {
            return !atEnd;
        }

        @Override
        public AnyType next() {
            if (expectedCount != modCount) {
                throw new ConcurrentModificationException();
            }

            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            AnyType resElement = current.element;
            previous = current;

            if (current.right != null) {
                // 下一个结点是它的右子树
                current = findMin(current.right);
            } else {
                // 下一个结点在它的上面，但不一定是父结点
                BinaryNode<AnyType> child = current;
                current = current.parent;
                // 这个设计的很巧妙，可以找到另一个位置
                while (current != null && current.left != child) {
                    child = current;
                    current = current.parent;
                }
                if (current == null) {
                    atEnd = true;
                }
            }

            okToRemove = true;

            return resElement;
        }

        @Override
        public void remove() {
            if (modCount != expectedCount) {
                throw new ConcurrentModificationException();
            }

            if (!okToRemove) {
                throw new IllegalStateException();
            }

            MyTreeSet.this.remove(previous.element);
            if (previous.right != null) {
                current = previous;
            }
            expectedCount++;
            okToRemove = false;
        }
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

    private void preorderShow(BinaryNode<AnyType> t) {
        if (t == null) {
            return;
        }
        System.out.print(t.element + " ");
        preorderShow(t.left);
        preorderShow(t.right);
    }

    private void postorderShow(BinaryNode<AnyType> t) {
        if (t == null) {
            return;
        }

        postorderShow(t.left);
        postorderShow(t.right);
        System.out.print(t.element + " ");
    }

    // 此处为适应练习 4.12，将该结点类的可见度调高了，实际上应该是 private，
    // 但是笔者设计水平有限，请各位理解，要注意各位不要乱暴露类，也不要在同一个
    // 包下随意使用这个类！
    class BinaryNode<AnyType> {
        public AnyType element;
        public BinaryNode<AnyType> left;
        public BinaryNode<AnyType> right;
        public BinaryNode<AnyType> parent;

        public BinaryNode(AnyType data) {
            this(data, null, null, null);
        }

        public BinaryNode(AnyType element, BinaryNode<AnyType> left,
                          BinaryNode<AnyType> right, BinaryNode<AnyType> parent) {
            this.element = element;
            this.left = left;
            this.right = right;
            this.parent = parent;
        }
    }
}
