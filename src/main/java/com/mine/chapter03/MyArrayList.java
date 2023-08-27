package com.mine.chapter03;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * @author CaoY
 * @date 2023-08-21 20:47
 * @description 自定义的 ArrayList（源自书 P47 - P48）
 * main 函数为笔者补充的测试用例
 */
public class MyArrayList<AnyType> implements Iterable<AnyType> {

    private static final int DEFAULT_CAPACITY = 10; // 默认的底层数组的大小

    private int theSize; // 实际上元素的个数
    private AnyType[] theItems; // 存放元素的底层数组
    private int modCount; // 修改次数

    public MyArrayList() {
        doClear();
    }

    public void clear() {
        doClear();
    }

    private void doClear() {
        theSize = 0;
        ensureCapacity(DEFAULT_CAPACITY);
        modCount++;
    }

    public int size() {
        return theSize;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * 调整尺寸
     */
    public void trimToSize() {
        ensureCapacity(size());
    }

    /**
     * 根据下标来获取相应位序的元素
     * @param index 传入的下标，若其为负数或大于等于实际的元素个数，则抛出 ArrayIndexOutOfBoundsException
     * @return      相应下标的元素
     */
    public AnyType get(int index) {
        if (index < 0 && index >= size()) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return theItems[index];
    }

    /**
     * 设置相应下标的元素的值，并将旧值返回
     * @param index     传入的下标，若其为负数或大于等于实际的元素个数，则抛出 ArrayIndexOutOfBoundsException
     * @param newVal    设置的新值
     * @return          该位序上的元素的旧值
     */
    public AnyType set(int index, AnyType newVal) {
        if (index < 0 && index >= size()) {
            throw new ArrayIndexOutOfBoundsException();
        }
        AnyType oldVal = theItems[index];
        theItems[index] = newVal;
        modCount++;
        return oldVal;
    }

    public void ensureCapacity(int newCapacity) {
        // 这里用 < 的用意是为了使得 newCapacity 至少与 size() 相等的时候才能分配新空间，
        // 进而保证空间够拷贝原有元素，可是笔者认为当二者空间相等的时候就没有必要重新分配，因为
        // 拷贝操作也挺耗时间的，不过此处为了保证与原代码一致，就不作修改
        if (newCapacity < size()) {
            return;
        }

        AnyType[] old = theItems;
        theItems = (AnyType[]) (new Object[newCapacity]); // 这里注意含泛型的数组分配空间时的写法，不能直接 new AnyType[]
        for (int i = 0; i < size(); i++) {
            theItems[i] = old[i];
        }
    }

    /**
     * 将指定可迭代集合的所有元素添加到 MyArrayList 集合中
     * @param items 可以迭代的集合
     */
    public void addAll(Iterable<? extends AnyType> items) {
        Iterator<? extends AnyType> iter = items.iterator();
        while (iter.hasNext()) {
            add(iter.next());
        }
    }

    /**
     * 从尾部插入指定元素，并返回 true
     * @param val   添加的新值
     * @return      如果运行正常，恒定为 true；负责会抛出异常
     */
    public boolean add(AnyType val) {
        add(size(), val);
        return true;
    }

    /**
     * 在指定的下标处添加元素，但是注意这里的下标要用户自己保证其有效性和正确性（回答练习 3.9）
     * @param index 下标
     * @param val   添加的值
     */
    public void add(int index, AnyType val) {
        if (theItems.length == size()) {
            // 两倍扩充容量，+ 1 则是适用于大小为 0 的情况
            ensureCapacity(size() * 2 + 1);
        }

        for (int i = theSize; i > index ; i--) {
            theItems[i] = theItems[i - 1];
        }

        theItems[index] = val;
        theSize++;
        modCount++;
    }

    /**
     * 根据指定的下标删除元素，返回删除的元素，这里的下标需要用户自己确保有效性和正确性
     * @param index 指定的待删除的下标
     * @return      删除的元素
     */
    public AnyType remove(int index) {
        AnyType removeItem = theItems[index];
        for (int i = index; i < size() - 1; i++) {
            theItems[i] = theItems[i + 1];
        }

        theSize--;
        modCount++;
        return removeItem;
    }

    @Override
    public Iterator<AnyType> iterator() {
        return new ArrayListIterator();
    }

    public ListIterator<AnyType> listIterator() {
        return new MyArrayListIterator();
    }

    // 返回一个反向迭代器
    public Iterator<AnyType> reverseIterator() {
        return new MyArrayListReverseIterator();
    }

    private class ArrayListIterator implements Iterator<AnyType> {

        private int current = 0; // 指向当前的元素的下标
        private int expectedCount = modCount; // 对于迭代器的严格检测，回答练习 3.17
        private boolean okToRemove = false; // 对于迭代器的严格检测，回答练习 3.17

        @Override
        public boolean hasNext() {
            return current < size();
        }

        @Override
        public AnyType next() {
            if (expectedCount != modCount) {
                throw new ConcurrentModificationException();
            }
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            okToRemove = true;
            return theItems[current++];
        }

        @Override
        public void remove() {
            if (expectedCount != modCount) {
                throw new ConcurrentModificationException();
            } if (!okToRemove) {
                throw new IllegalStateException();
            }
            expectedCount++;
            MyArrayList.this.remove(--current);
        }
    }

    // 回答练习 3.13
    private class MyArrayListIterator implements ListIterator<AnyType> {

        private int current = 0; // 指向当前元素的下标
        private boolean backwards = false; // 如果是调用 next 迭代，则返回 false；否则返回 true

        @Override
        public boolean hasNext() {
            return current < size();
        }

        @Override
        public AnyType next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            backwards = false;
            return theItems[current++];
        }

        @Override
        public boolean hasPrevious() {
            return current > 0;
        }

        @Override
        public AnyType previous() {
            if (!hasPrevious()) {
                throw new NoSuchElementException();
            }
            backwards = true;
            return theItems[--current];
        }

        @Override
        public int nextIndex() {
            // 注：原来接口注释定义，如果迭代器位于最后一个位置，将返回列表大小
            return current;
        }

        @Override
        public int previousIndex() {
            // 注：原来接口注释定义，如果迭代器位于开头，则返回 -1
            return current - 1;
        }

        @Override
        public void remove() {
            if (backwards) {
                MyArrayList.this.remove(current);
            } else {
                MyArrayList.this.remove(--current);
            }
        }

        @Override
        public void set(AnyType anyType) {
            theItems[current - 1] = anyType;
        }

        @Override
        public void add(AnyType anyType) {
            MyArrayList.this.add(current++, anyType);
        }
    }

    // 反向迭代器内部类
    private class MyArrayListReverseIterator implements Iterator<AnyType> {

        private int current = size();

        @Override
        public boolean hasNext() {
            return current > 0;
        }

        @Override
        public AnyType next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return theItems[--current];
        }

        @Override
        public void remove() {
            MyArrayList.this.remove(current);
        }
    }

    public static void main(String[] args) {
        MyArrayList<Integer> list = new MyArrayList<>();

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

        list.trimToSize();
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
        System.out.print("删除所有偶数元素：");
        while (iter.hasNext()) {
            System.out.print(iter.next() + " ");
        }
        System.out.println();

        list.clear();
        System.out.println("list 是否为空：" + (list.isEmpty() ? "是" : "否"));
    }
}
