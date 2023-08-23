package com.mine.chapter03;

import java.util.NoSuchElementException;

/**
 * @author CaoY
 * @date 2023-08-23 19:00
 * @description 使用循环数组实现自定义队列（参考自书 P65 - P66）
 *
 * 注：使用的数组需要预留一个位置存放供 back 指向，因为 back 指向最后一个元素的下一个位置。
 * 所以当队列为空时，front 和 back 指向同一个位置，所以每次出队操作时最好应该检测一下队列是否
 * 为空，否则就会返回数组中无效的部分，从而可能引发一些安全风险
 */
public class MyArrayListQueue<AnyType> implements MyQueue<AnyType> {

    private AnyType[] theArray; // 存放数据的数组，整个数组留空一个位置以判断
    private int front; // 队头下标，指向队列第一个元素，但是在初始的时候也没有元素
    private int back; // 队尾下标，指向队列最后一个元素的后一个位置
    private static final int DEFAULT_CAPACITY = 10; // theArray 数组的默认初始大小

    public MyArrayListQueue() {
        doClear();
    }

    @Override
    public boolean enQueue(AnyType x) {
        if (theArray.length - 1 == size()) {
            // 两倍扩充容量，+ 2 则是适用于大小为 0 的情况
            ensureCapacity(size() * 2 + 2);
        }

        theArray[back] = x;
        back = (back + 1) % theArray.length;
        return true;
    }

    @Override
    public AnyType deQueue() {
        if (size() <= 0) {
            throw new NoSuchElementException();
        }
        AnyType topVal = theArray[front];
        front = (front + 1) % theArray.length;
        return topVal;
    }

    @Override
    public AnyType front() {
        AnyType topVal = theArray[front];
        return topVal;
    }

    @Override
    public int size() {
        // 通过队头和队尾后一个元素的下标动态计算队列中的元素个数
        return front <= back ? back - front : theArray.length - front + back + 1;
    }

    @Override
    public boolean isEmpty() {
        // 因为预留一个数组空间不存放元素，专门用于 back 下标指向，所以下面的条件可以作为
        return front == back;
    }

    @Override
    public void clear() {
        doClear();
    }

    private void doClear() {
        front = back = 0;
        ensureCapacity(DEFAULT_CAPACITY);
    }

    private void ensureCapacity(int newCapacity) {
        // 这里用 < 的用意是为了使得 newCapacity 至少与 size() 相等的时候才能分配新空间，
        // 进而保证空间够拷贝原有元素，可是笔者认为当二者空间相等的时候就没有必要重新分配，因为
        // 拷贝操作也挺耗时间的，不过此处为了保证与原代码一致，就不作修改
        if (newCapacity < size()) {
            return;
        }

        AnyType[] old = theArray;
        theArray = (AnyType[]) (new Object[newCapacity]); // 这里注意含泛型的数组分配空间时的写法，不能直接 new AnyType[]
        for (int i = front; i != back; i = (i + 1) % theArray.length) {
            theArray[i] = old[i];
        }
    }

    public static void main(String[] args) {
        MyQueue<Integer> queue = new MyArrayListQueue<>();

        // System.out.println(queue.deQueue()); // 用于测试最初没有插入元素的情况

        for (int i = 0; i < 100; i++) {
            queue.enQueue(i + 1);
        }

        System.out.println("对头的元素：" + queue.front());
        System.out.println("元素个数：" + queue.size());
        System.out.println("队列是否为空：" + (queue.isEmpty() ? "是" : "否"));
        System.out.print("依次出队所有元素：");
        while (!queue.isEmpty()) {
            System.out.print(queue.deQueue() + " ");
        }
        System.out.println();

        System.out.println("出队所有元素后，队列是否为空：" + (queue.isEmpty() ? "是" : "否"));
        queue.enQueue(235);
        queue.enQueue(334);
        queue.enQueue(576);
        System.out.println("请空前，元素个数：" + queue.size());
        queue.clear();
        System.out.println("清空后，队列是否为空：" + (queue.isEmpty() ? "是" : "否"));

        // System.out.println(queue.deQueue()); // 为了测试 clear() 后的队列内部的数组的情况
    }
}
