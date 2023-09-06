package com.mine.chapter04;

import java.util.Comparator;
import java.util.NoSuchElementException;

/**
 * @author CaoY
 * @date 2023-09-05 22:13
 * @description AVL 树的实现（参考自书 P86 - P94）
 *
 * 照抄书首次实现 AVL 树，但是对于其中的 remove 情况还认识不足，再加上图比较难画，所以对于这个 remove
 * 的测试情况感觉还不到位，后面需要进一步测试。todo
 */
public class AVLTree<AnyType extends Comparable<? super AnyType> > {

    private AVLNode<AnyType> root;
    private Comparator<? super AnyType> cmp;
    private static final int ALLOWED_IMBALANCE = 1; // 每个左右子树允许的最大的高度差

    public AVLTree() {
        root = null;
    }

    public AVLTree(Comparator<? super AnyType> cmp) {
        this.root = null;
        this.cmp = cmp;
    }

    private int myCompare(AnyType a, AnyType b) {
        if (cmp != null) {
            return cmp.compare(a, b);
        } else {
            return a.compareTo(b);
        }
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

    /**
     * 在 AVL 树中插入指定的值，若该值已经存在于树中，则不作任何操作。
     * 注：插入值后仍为一棵 AVL 树
     * @param x 指定待插入的值
     */
    public void insert(AnyType x) {
        root = insert(x, root);
    }

    /**
     * 在 AVL 树中删除指定的值
     * 注：删除指定值后仍为一棵 AVL 树
     * @param x 指定待删除的值
     */
    public void remove(AnyType x) {
        root = remove(x, root);
    }

    /**
     * 输出指定描述并按前序遍历打印树中的元素
     * @param description   对于打印的 AVL 树的描述
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
     * 根据指定描述依次采用三种遍历方式打印 AVL 树
     * @param description   指定描述
     */
    public void allOrderShow(String description) {
        System.out.println(description);
        preorderShow("前序遍历打印：");
        inorderShow("中序遍历打印：");
        postorderShow("后序遍历打印：");
        System.out.println("***************************************");
    }

    private boolean contains(AnyType x, AVLNode<AnyType> t) {
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

    private AVLNode<AnyType> findMin(AVLNode<AnyType> t) {
        if (t == null) {
            return null;
        }

        AVLNode<AnyType> minNode = findMin(t.left);

        return minNode == null ? t : minNode;
    }

    private AVLNode<AnyType> findMax(AVLNode<AnyType> t) {
        if (t == null) {
            return null;
        }

        AVLNode<AnyType> maxNode = findMax(t.right);

        return maxNode == null ? t : maxNode;
    }

    private AVLNode<AnyType> insert(AnyType x, AVLNode<AnyType> t) {
        if (t == null) {
            return new AVLNode<>(x, null, null);
        }

        int compareRes = myCompare(x, t.element);

        if (compareRes < 0) {
            t.left = insert(x, t.left);
        } else if (compareRes > 0) {
            t.right = insert(x, t.right);
        } else {
            // 不做任何事
        }

        t = balance(t);
        return t;
    }

    private AVLNode<AnyType> remove(AnyType x, AVLNode<AnyType> t) {
        if (t == null) {
            return t;
        }

        int compareRes = myCompare(x, t.element);
        if (compareRes < 0) {
            t.left = remove(x, t.left);
        } else if (compareRes > 0) {
            t.right = remove(x, t.right);
        } else if (t.left != null && t.right != null) { // 要被删除的结点有两个子树
            t.element = findMin(t.right).element;
            t.right = remove(t.element, t.right);
        } else {
            t = (t.left != null) ? t.left : t.right;
        }

        return balance(t);
    }

    private AVLNode<AnyType> balance(AVLNode<AnyType> t) {
        if (t == null) {
            return t;
        }

        if (height(t.left) - height(t.right) > ALLOWED_IMBALANCE) {
            // 左子树挂多了
            if (height(t.left.left) >= height(t.left.right)) {// 之所以用 =，是为了调整删除造成的一些不平衡的情况，如：删除一个结点后导致左右子树高度差距为 2
                // 需要单旋转
                t = rotateWithLeftChild(t);
            } else {
                // 需要双旋转
                t = doubleWithLeftChild(t);
            }
        } else if (height(t.right) - height(t.left) > ALLOWED_IMBALANCE) {
            // 右子树挂多了
            if (height(t.right.right) >= height(t.right.left)) {
                // 需要单旋转
                t = rotateWithRightChild(t);
            } else {
                // 需要双旋转
                t = doubleWithRightChild(t);
            }
        }

        // 调整当前结点的高度
        t.height = Math.max(height(t.left), height(t.right)) + 1;
        return t;
    }

    // 左左的情况：单次左旋结点，本质上就是变换引用，参考书中图 4-31
    private AVLNode<AnyType> rotateWithLeftChild(AVLNode<AnyType> k2) {
        AVLNode<AnyType> k1 = k2.left;
        k2.left = k1.right;
        k1.right = k2;

        // 调整 k1、k2 的高度
        k2.height = Math.max(height(k2.left), height(k2.right)) + 1;
        k1.height = Math.max(k2.height, height(k1.left)) + 1;

        return k1;
    }

    // 右右的情况：单次旋转右结点，参考书中图 4-33
    private AVLNode<AnyType> rotateWithRightChild(AVLNode<AnyType> k1) {
        AVLNode<AnyType> k2 = k1.right;
        k1.right = k2.left;
        k2.left = k1;

        // 调整 k1、k2的高度
        k1.height = Math.max(height(k1.left), height(k1.right)) + 1;
        k2.height = Math.max(k1.height, height(k2.right)) + 1;

        return k2;
    }

    // 左右的情况：先右旋变为左左的情况，再左旋，参考书中图 4-35
    private AVLNode<AnyType> doubleWithLeftChild(AVLNode<AnyType> k3) {
        AVLNode<AnyType> k1 = k3.left;// 之所以给出 k1、k2 是为了与图对应上
        k3.left = rotateWithRightChild(k1);
        AVLNode<AnyType> k2 = k3.left;

        k2 = rotateWithLeftChild(k3);
        return k2;
    }

    // 右左的情况：先左旋变为右右的情况，再右旋，参考书中图 4-36
    private AVLNode<AnyType> doubleWithRightChild(AVLNode<AnyType> k1) {
        AVLNode<AnyType> k3 = k1.right; // 之所以给出 k3、k4 是为了与图对应上
        k1.right = rotateWithLeftChild(k3);
        AVLNode<AnyType> k2 = k1.right;

        k2 = rotateWithRightChild(k1);
        return k2;
    }

    /**
     * 返回结点的高度，如果结点为 null，则返回 -1
     * @param t
     * @return
     */
    private int height(AVLNode<AnyType> t) {
        return t == null ? -1 : t.height;
    }

    private void preorderShow(AVLNode<AnyType> t) {
        if (t == null) {
            return;
        }
        System.out.print(t.element + " ");
        preorderShow(t.left);
        preorderShow(t.right);
    }

    private void inorderShow(AVLNode<AnyType> t) {
        if (t == null) {
            return;
        }

        inorderShow(t.left);
        System.out.print(t.element + " ");
        inorderShow(t.right);
    }

    private void postorderShow(AVLNode<AnyType> t) {
        if (t == null) {
            return;
        }

        postorderShow(t.left);
        postorderShow(t.right);
        System.out.print(t.element + " ");
    }

    private static class AVLNode<AnyType> {
        public AnyType element;
        public AVLNode<AnyType> left;
        public AVLNode<AnyType> right;
        public int height;

        public AVLNode(AnyType element) {
            this(element, null, null);
        }

        public AVLNode(AnyType element, AVLNode<AnyType> left, AVLNode<AnyType> right) {
            this.element = element;
            this.left = left;
            this.right = right;
            this.height = 0;
        }
    }

    public static void main(String[] args) {
        // 下面的代码主要验证 insert 和 remove 的正确性，其他的均与 BinarySearchTree 中的相同，
        // 并且之前已经测试过，故除 insert 和 remove 外不作测试。而对于 insert 的正确性，可参考书
        // P88 - P90 的图，通过前、中、后序遍历即可确定 insert 后 AVL 树的正确性，但是对于 remove
        // 方法，因为没有图支持，而且图比较难画，我能做的测试比较有限，期待后续改进，进一步测试多种情况（todo：remove 进一步测试）。
        AVLTree<Integer> tree = new AVLTree<>();
        tree.insert(2);
        tree.insert(1);
        tree.insert(3);
        tree.allOrderShow("插入 1、2、3 后：");
        tree.insert(4);
        tree.allOrderShow("插入 4 后：");
        tree.insert(5);
        tree.allOrderShow("插入 5 后：");
        tree.insert(6);
        tree.allOrderShow("插入 6 后：");
        tree.insert(7);
        tree.allOrderShow("插入 7 后：");
        tree.insert(16);
        tree.insert(15);
        tree.allOrderShow("插入 16、15 后：");
        tree.insert(14);
        tree.allOrderShow("插入 14 后：");
        tree.insert(13);
        tree.allOrderShow("插入 13 后：");
        tree.insert(12);
        tree.allOrderShow("插入 12 后：");
        tree.insert(11);
        tree.insert(10);
        tree.insert(8);
        tree.allOrderShow("插入 11、10、8 后：");
        tree.insert(9);
        tree.allOrderShow("插入 9 后：");
//        tree.remove(12);
//        tree.allOrderShow("删除 12 后：");
//        tree.remove(13);
//        tree.allOrderShow("删除 13 后：");
        tree.remove(11);
        tree.allOrderShow("删除 11 后：");
    }
}
