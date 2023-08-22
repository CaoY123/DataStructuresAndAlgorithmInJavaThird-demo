package com.mine.chapter03;

/**
 * @author CaoY
 * @date 2023-08-22 23:54
 * @description 自定义的队列接口
 */
public interface MyQueue<AnyType> {

    boolean enQueue(AnyType x);

    AnyType deQueue();

    AnyType front();

    int size();

    boolean isEmpty();

    void clear();
}
