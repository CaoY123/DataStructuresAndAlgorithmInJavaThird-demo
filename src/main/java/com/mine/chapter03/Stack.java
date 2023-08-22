package com.mine.chapter03;

/**
 * @author CaoY
 * @date 2023-08-22 21:49
 * @description 自定义的栈接口
 */
public interface Stack<AnyType> {

    /**
     * 将元素压入栈，在不抛出任何异常的情况下返回 true
     * @param x 待入栈的元素
     * @return
     */
    boolean push(AnyType x);

    /**
     * 将栈顶元素弹出，并返回栈顶元素
     * @return  弹出的栈顶元素
     */
    AnyType pop();

    /**
     * 获取栈顶元素
     * @return  栈顶元素
     */
    AnyType top();

    boolean isEmpty();

    int size();

    /**
     * 清空栈
     */
    void clear();
}
