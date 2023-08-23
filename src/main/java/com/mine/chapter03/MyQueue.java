package com.mine.chapter03;

/**
 * @author CaoY
 * @date 2023-08-22 23:54
 * @description 自定义的队列接口
 */
public interface MyQueue<AnyType> {

    /**
     * 入队指定元素
     * @param x 代入队的元素
     * @return  若不抛出任何异常，则返回 true
     */
    boolean enQueue(AnyType x);

    /**
     * 队头元素出队
     * @return  队头元素
     */
    AnyType deQueue();

    /**
     * 获得队头元素
     * @return  队头元素
     */
    AnyType front();

    /**
     * 队列中的元素个数
     * @return 队列中的元素个数
     */
    int size();

    /**
     * 判断对列是否为空
     * @return  对列为空，返回 true；反之，返回 false
     */
    boolean isEmpty();

    /**
     * 清空队列
     */
    void clear();
}
