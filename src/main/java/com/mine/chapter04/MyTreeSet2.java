package com.mine.chapter04;

import java.util.Comparator;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**thi
 * @author CaoY
 * @date 2023-09-09 23:43
 * @description 线索二叉查找树
 */
public class MyTreeSet2<AnyType extends Comparable<? super AnyType> >
implements Iterable<AnyType>{

    private BinaryNode2<AnyType> root;
    private int modCount;
    private Comparator<? super AnyType> cmp; //比较器

    public MyTreeSet2() {
        root = null;
        modCount = 0;
        cmp = null;
    }

    /**
     * 根据自定义的比较规则定义 TreeSet2 的排序
     * @param cmp   自定义的比较器
     */
    public MyTreeSet2(Comparator<? super AnyType> cmp) {
        this.cmp = cmp;
        root = null;
        modCount = 0;
    }

    /**
     * 将该 TreeSet2 置空
     */
    public void makeEmpty() {
        root = null;
        modCount++;
    }

    /**
     * 判断该 TreeSet2 对象是否为空
     * @return  若为空，返回 true；反之，则为 false
     */
    public boolean isEmpty() {
        return root == null;
    }

    /**
     * 判断该树中是否包含指定值
     * @param x 指定直
     * @return  若包含指定值，返回 true；反之，则为 false
     */
    public boolean contains(AnyType x) {
        if (x == null) {
            return false;
        }

        return contains(x, root);
    }

    /**
     * 求该 TreeSet2 的最小值
     * @return 该 TreeSet2 的最小值
     * @throws NoSuchElementException 如果该 TreeSet2 为空，则抛出该异常
     */
    public AnyType findMin() throws NoSuchElementException{
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        return findMin(root).element;
    }

    /**
     * 求该 TreeSet2 的最大值
     * @return 该 TreeSet2 的最大值
     * @throws NoSuchElementException 如果该 TreeSet2 为空，则抛出该异常
     */
    public AnyType findMax() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        return findMax(root).element;
    }

    /**
     * 往 TreeSet2 中插入指定值
     * @param x 指定待插入的值
     */
    public void insert(AnyType x) {
        if (x == null) {
            throw new IllegalArgumentException("实参为 null");
        }

        root = insert(x, root, null, null);
    }

    /**
     * 删除指定的值
     * @param x 待删除的值
     */
    public void remove(AnyType x) {
        if (x == null) {
            throw new IllegalArgumentException("实参为 null");
        }

        if (isEmpty()) {
            return;
        }

        root = remove(x, root);
    }

    @Override
    public Iterator<AnyType> iterator() {
        return new MyTreeSet2Iterator();
    }

    /**
     * 输出指定描述并按前序遍历打印树中的元素
     * @param description   对于打印树的描述
     */
    public void preorderShow(String description) {
        System.out.print(description);
        if (isEmpty()) {
            System.out.print("这是一个空的 TreeSet");
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
            System.out.print("这是一个空的 TreeSet");
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
            System.out.print("这是一个空的 TreeSet");
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

    // 排序方法
    private int myCompare(AnyType a, AnyType b) {
        if (cmp != null) {
            return cmp.compare(a, b);
        } else {
            return a.compareTo(b);
        }
    }

    private boolean contains(AnyType x, BinaryNode2<AnyType> t) {
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

    private BinaryNode2<AnyType> findMin(BinaryNode2<AnyType> t) {
        if (t == null) {
            return null;
        }

        BinaryNode2<AnyType> node = findMin(t.left);
        return node == null ? t : node;
    }

    private BinaryNode2<AnyType> findMax(BinaryNode2<AnyType> t) {
        if (t == null) {
            return null;
        }

        BinaryNode2<AnyType> node = findMax(t.right);
        return node == null ? t : node;
    }

    private BinaryNode2<AnyType> insert(AnyType x, BinaryNode2<AnyType> t,
                                        BinaryNode2<AnyType> pv, BinaryNode2<AnyType> nt) {
        if (t == null) {
            modCount++;
            t = new BinaryNode2<>(x, null, null, pv, nt);
            if (nt != null) {
                nt.prev = t;
            }
            if (pv != null) {
                pv.next = t;
            }
            return t;
        }

        int compareRes = myCompare(x, t.element);
        if (compareRes < 0) {
            t.left = insert(x, t.left, pv, t);
        } else if (compareRes > 0) {
            t.right = insert(x, t.right, t, nt);
        } else {
            // 什么也不做
        }

        return t;
    }

    private BinaryNode2<AnyType> remove(AnyType x, BinaryNode2<AnyType> t) {
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
            BinaryNode2<AnyType> pv = t.prev;
            BinaryNode2<AnyType> nt = t.next;
            if (pv != null) {
                pv.next = nt;
            }
            if (nt != null) {
                nt.prev = pv;
            }

            t = (t.left != null) ? t.left : t.right;
        }

        return t;
    }

    private void inorderShow(BinaryNode2<AnyType> t) {
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

    private void preorderShow(BinaryNode2<AnyType> t) {
        if (t == null) {
            return;
        }
        System.out.print(t.element + " ");
        preorderShow(t.left);
        preorderShow(t.right);
    }

    private void postorderShow(BinaryNode2<AnyType> t) {
        if (t == null) {
            return;
        }

        postorderShow(t.left);
        postorderShow(t.right);
        System.out.print(t.element + " ");
    }

    private class MyTreeSet2Iterator implements Iterator<AnyType> {
        private BinaryNode2<AnyType> current;
        private BinaryNode2<AnyType> previous;
        private int expectedCount;
        private boolean okToRemove;
        private boolean atEnd;

        public MyTreeSet2Iterator() {
            current = findMin(root);
            previous = null;
            expectedCount = modCount;
            okToRemove = false;
            atEnd = false;
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

            AnyType res = current.element;
            previous = current;
            current = current.next;

            if (current == null) {
                atEnd = true;
            }

            okToRemove = true;
            return res;
        }

        @Override
        public void remove() {
            if (expectedCount != modCount) {
                throw new ConcurrentModificationException();
            }
            if (!okToRemove) {
                throw new IllegalStateException();
            }

            MyTreeSet2.this.remove(previous.element);
            expectedCount++;
            okToRemove = false;
        }
    }

    private static class BinaryNode2<AnyType> {
        public AnyType element;
        public BinaryNode2<AnyType> left;
        public BinaryNode2<AnyType> right;
        public BinaryNode2<AnyType> prev;
        public BinaryNode2<AnyType> next;

        public BinaryNode2(AnyType element) {
            this(element, null, null, null, null);
        }

        public BinaryNode2(AnyType element, BinaryNode2<AnyType> left, BinaryNode2<AnyType> right,
                           BinaryNode2<AnyType> prev, BinaryNode2<AnyType> next) {
            this.element = element;
            this.left = left;
            this.right = right;
            this.prev = prev;
            this.next = next;
        }
    }
}
