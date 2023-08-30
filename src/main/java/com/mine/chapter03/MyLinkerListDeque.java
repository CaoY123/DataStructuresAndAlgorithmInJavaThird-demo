package com.mine.chapter03;

/**
 * @author CaoY
 * @date 2023-08-30 19:27
 * @description 练习 3.28 实现一个 deque（双端队列）
 *
 * 内部使用一个前面实现的 MyLinkedList 链表类就可以模拟出 deque 操作，而且下列操作均在常数时间内完成。
 * 注：MyLinkedList 的 getNode 方法会根据索引判断从最前面开始索引还是从最后面开始索引，因为 deque
 * 的操作只与最前面和最后面的元素有关，所以在找操作的位置时总是可以一下子就找到。
 */
public class MyLinkerListDeque<AnyType> {

    private MyLinkedList<AnyType> dataList;

    public MyLinkerListDeque() {
        dataList = new MyLinkedList<>();
    }

    /**
     * 向双端队列最前端插入元素
     * @param x 指定要插入的元素
     * @return  在不抛出异常的情况下，返回 true
     */
    public boolean push(AnyType x) {
        dataList.add(0, x);
        return true;
    }

    /**
     * 从双端队列中删除最前端项并将其返回
     * @return  最前端的项
     */
    public AnyType pop() {
        return dataList.remove(0);
    }

    /**
     * 向双端队列后端插入指定元素
     * @param x 指定要插入的元素
     * @return  在不抛出异常的情况下，返回 true
     */
    public boolean inject(AnyType x) {
        return dataList.add(x);
    }

    /**
     * 从双端队列中删除尾端项并将其返回
     * @return  删除的尾端项
     */
    public AnyType eject() {
        return dataList.remove(dataList.size() - 1);
    }

    /**
     * 判断双端队列是否为空
     * @return  若为空，返回 true；反之，返回 false
     */
    public boolean isEmpty() {
        return dataList.isEmpty();
    }

    public int size() {
        return dataList.size();
    }

    public void clear() {
        dataList.clear();
    }
}
