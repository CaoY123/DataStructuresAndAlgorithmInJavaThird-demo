package com.mine.chapter03;

import java.util.*;

/**
 * @author CaoY
 * @date 2023-08-22 0:08
 * @description 自定义的 LinkedList（源自书 P54 - P58）
 *
 * 下面测试中没有模拟多线程修改集合的情况，因为目前我对多线程这块内容还不熟悉，等后面有机会看看能不能补充一下
 * todo 该自定义集合类的线程安全测试
 */
public class MyLinkedList<AnyType> implements Iterable<AnyType> {

    private int theSize; // 链表的元素个数
    private int modCount; // 修改次数，为保证并发时的线程安全
    private Node<AnyType> beginMarker; // 头结点引用
    private Node<AnyType> endMarker; // 尾结点引用

    public MyLinkedList() {
        doClear();
    }

    /**
     * 清空链表并将修改次数 + 1
     */
    public void clear() {
        doClear();
    }

    private void doClear() {
        beginMarker = new Node<>(null, null, null);
        endMarker = new Node<>(null, beginMarker, null);
        beginMarker.next = endMarker;

        theSize = 0;
        modCount++;
    }

    /**
     * 获取链表中的元素个数
     * @return  链表中的元素个数
     */
    public int size() {
        return theSize;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * 在结尾添加指定的值
     * @param x 待添加的值
     * @return  在不抛出异常的情况下返回 true
     */
    public boolean add(AnyType x) {
        add(size(), x);
        return true;
    }

    /**
     * 在指定的下标处添加元素值
     * @param index 指定的下标，从 0 开始
     * @param x     待添加的元素
     */
    public void add(int index, AnyType x) {
        addBefore(getNode(index, 0, size()), x);
    }

    /**
     * 获取链表中相应下标的元素值（）
     * @param index 指定的下标，从 0 开始
     * @return      相应下标处结点的元素值
     */
    public AnyType get(int index) {
        return getNode(index).data;
    }

    /**
     * 重新设置并返回指定下标的元素
     * @param index     指定的下标
     * @param newVal    新值
     * @return          指定下标处结点的旧元素值
     */
    public AnyType set(int index, AnyType newVal) {
        Node<AnyType> p = getNode(index);
        AnyType oldVal = p.data;
        p.data = newVal;
        return oldVal;
    }

    /**
     * 删除表中的存在于指定可迭代集合中的元素（回答练习 3.10）
     * @param items 指定的可迭代集合
     */
    public void removeAll(Iterable<? extends AnyType> items) {
        Iterator<? extends AnyType> iter = items.iterator();

        while (iter.hasNext()) {
            AnyType val = iter.next();
            Iterator<AnyType> thisIter = iterator();
            AnyType thisVal = null;
            if (thisIter.hasNext()) {
                thisVal = thisIter.next();
            }
            while (thisIter.hasNext() && !Objects.equals(val, thisVal)) {
                thisVal = thisIter.next();
            }
            if (Objects.equals(val, thisVal)) {
                thisIter.remove();
            }
        }
    }

    /**
     * 删除指定下标的结点，返回被删除的结点的元素值
     * @param index 指定的下标，从 0 开始
     * @return      被删除结点的元素值
     */
    public AnyType remove(int index) {
        return remove(getNode(index));
    }

    /**
     * 在指定结点前插入新值
     * @param p 指定的结点
     * @param x 待插入的值
     */
    private void addBefore(Node<AnyType> p, AnyType x) {
        Node<AnyType> newNode = new Node<>(x, p.prev, p);
        newNode.prev.next = newNode;
        p.prev = newNode;

        theSize++;
        modCount++;
    }

    /**
     * 删除指定的结点并返回其元素值
     * @param p 待删除的结点
     * @return  待删除结点的元素值
     */
    private AnyType remove(Node<AnyType> p) {
        // 断开与前后结点的联系
        p.prev.next = p.next;
        p.next.prev = p.prev;

        // 这里为了配合迭代器的 remove()，绝对不能写下面这两句，那会破坏 current 引用的指向，最终导致迭代器无法使用
        // p.prev = null;
        // p.next = null;

        theSize--;
        modCount++;

        return p.data;
    }

    /**
     * 获取指定下标处的结点
     * @param index 指定的下标
     * @return      指点下标处的结点
     */
    private Node<AnyType> getNode(int index) {
        return getNode(index, 0, size() - 1);
    }

    /**
     * 获取指定下标处的结点，其中下标必须处于指定的范围，否则抛出 IndexOutOfBoundsException
     * @param index 指定的下标
     * @param lower 指定范围的下界
     * @param upper 指定范围的上界
     * @return      指定下标处的结点
     */
    private Node<AnyType> getNode(int index, int lower, int upper) {
        Node<AnyType> p;
        if (index < lower || index > upper) {
            throw new IndexOutOfBoundsException();
        }

        if (index < size() / 2) {
            // 下标位于链表的前半部分，从前往后遍历
            p = beginMarker.next;
            for (int i = 0; i < index; i++) {
                p = p.next;
            }
        } else{
            // 下标位于链表的后半部分，从后往前遍历
            p = endMarker;
            for (int i = size(); i > index; i--) {
                p = p.prev;
            }
        }

        return p;
    }

    /**
     * 集合中是否包含指定元素，若包含，返回 true，反之，则返回 false。
     * @param x 指定的判断是否包含的值，可以为 null
     * @return  判断是否包含的结果
     * 注：练习 3.3 的回答
     */
    public boolean contains(AnyType x) {
        Node node = beginMarker.next;
        while (node != endMarker) {
            if (Objects.equals(x, node.data)) {
                // 包含对当 x 是 null 值的处理，因为 JDK 中的 LinkedList 可以存放 null 值，
                // 并且当其含有 null 值时，调用 contains() 会返回 true
                return true;
            }
            node = node.next;
        }
        return false;
    }

    @Override
    public Iterator<AnyType> iterator() {
        return new LinkedListIterator();
    }

    public ListIterator<AnyType> listIterator() {
        return new MyLinkedListIterator();
    }

    private class LinkedListIterator implements Iterator<AnyType> {

        private Node<AnyType> current = beginMarker.next;
        private int expectedModCount = modCount;
        private boolean okToRemove = false; // 按照迭代器的使用规范，只有 next() 后才能 remove()，这个变量就是为了保证这个规则而存在的

        @Override
        public boolean hasNext() {
            return current != endMarker;
        }

        @Override
        public AnyType next() {
            if (modCount != expectedModCount) {
                throw new ConcurrentModificationException();
            }

            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            AnyType nextItem = current.data;
            current = current.next;
            okToRemove = true;
            return nextItem;
        }

        @Override
        public void remove() {
            if (modCount != expectedModCount) {
                throw new ConcurrentModificationException();
            }

            if (!okToRemove) {
                throw new IllegalStateException();
            }

            MyLinkedList.this.remove(current.prev);
            expectedModCount++;
            okToRemove = false;
        }
    }

    // 回答练习 3.14 （搞得有点乱，如有错误，恳请指正）
    private class MyLinkedListIterator implements ListIterator<AnyType> {

        private Node<AnyType> current = beginMarker.next;
        private int expectedModCount = modCount;
        private boolean okToRemove = false; // 按照迭代器的使用规范，只有 next() 后才能 remove()，这个变量就是为了保证这个规则而存在的
        private boolean backwards = false;

        @Override
        public boolean hasNext() {
            return current != endMarker;
        }

        @Override
        public AnyType next() {
            if (modCount != expectedModCount) {
                throw new ConcurrentModificationException();
            }
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            AnyType res = current.data;
            okToRemove = true;
            current = current.next;
            backwards = false;
            return res;
        }

        @Override
        public boolean hasPrevious() {
            return current.prev != beginMarker;
        }

        @Override
        public AnyType previous() {
            if (modCount != expectedModCount) {
                throw new ConcurrentModificationException();
            }
            if (!hasPrevious()) {
                throw new NoSuchElementException();
            }
            current = current.prev;
            AnyType res = current.data;
            okToRemove = true;
            backwards = true;
            return res;
        }

        @Override
        public int nextIndex() {
            // 不要求实现，抛异常了
            throw new UnsupportedOperationException();
        }

        @Override
        public int previousIndex() {
            // 不要求实现，抛异常了
            throw new UnsupportedOperationException();
        }

        @Override
        public void remove() {
            if (modCount != expectedModCount) {
                throw new ConcurrentModificationException();
            }
            if (!okToRemove) {
                throw new IllegalStateException();
            }

            MyLinkedList.this.remove(current.prev);
            expectedModCount++;
        }

        @Override
        public void set(AnyType anyType) {
            if (modCount != expectedModCount) {
                throw new ConcurrentModificationException();
            }

            if (backwards) {
                current.data = anyType;
            } else {
                current.prev.data = anyType;
            }
            modCount++;
            expectedModCount++;
        }

        /**
         * add 具有方向性，如果 next 迭代，它会插在刚经过的元素的后面；如果使用 previous 迭代
         * 它会插在 刚经过元素的前面
         * @param anyType   待插入的值
         */
        @Override
        public void add(AnyType anyType) {
            if (modCount != expectedModCount) {
                throw new ConcurrentModificationException();
            }

            expectedModCount++;
            if (backwards) {
                MyLinkedList.this.addBefore(current, anyType);
            } else {
                Node<AnyType> p = current;
                MyLinkedList.this.addBefore(p, anyType);
                current = current.prev;
            }
        }
    }

    /**
     * 双链表的结点类
     * @param <AnyType>
     */
    private static class Node<AnyType> {

        public Node(AnyType data, Node<AnyType> prev, Node<AnyType> next) {
            this.data = data;
            this.prev = prev;
            this.next = next;
        }

        public AnyType data; // 数据域
        public Node<AnyType> prev; // 指向前置结点的引用
        public Node<AnyType> next; // 指向后置结点的引用
    }

    public static void main(String[] args) {
        MyLinkedList<Integer> list = new MyLinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(5);

        Iterator<Integer> iter = list.iterator();
        System.out.print("尾插法插入元素后：");
        while (iter.hasNext()) {
            System.out.print(iter.next() + " ");
        }
        System.out.println();

        list.add(0, 0);
        list.add(4, 4);
        list.add(list.size(), 6);
        iter = list.iterator();
        System.out.print("特定下标插入元素后：");
        while (iter.hasNext()) {
            System.out.print(iter.next() + " ");
        }
        System.out.println();

        int forthVal = list.get(3);
        System.out.println("forthVal = " + forthVal);

        list.set(4, -4);
        int fifthVal = list.get(4);
        System.out.println("fifthVal = " + fifthVal);

        list.remove(list.size() - 1);
        iter = list.iterator();
        System.out.print("删除最后一个元素后：");
        while (iter.hasNext()) {
            System.out.print(iter.next() + " ");
        }
        System.out.println();

        iter = list.iterator();
        while (iter.hasNext()) {
            if (iter.next() % 2 == 0) {
                // 删除所有偶数的元素
                iter.remove();
            }
        }
        iter = list.iterator();
        System.out.print("迭代器删除所有偶数元素：");
        while (iter.hasNext()) {
            System.out.print(iter.next() + " ");
        }
        System.out.println();

        list.clear();
        System.out.println("list 是否为空：" + (list.isEmpty() ? "是" : "否"));

        List<Integer> list1 = new LinkedList<>();
        list1.add(1);
        System.out.println(list1);
        System.out.println(list1.contains(null));
    }
}
