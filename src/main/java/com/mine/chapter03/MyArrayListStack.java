package com.mine.chapter03;

import java.util.EmptyStackException;
import java.util.List;

/**
 * @author CaoY
 * @date 2023-08-22 21:47
 * @description 使用自定义的数组实现栈
 */
public class MyArrayListStack<AnyType> implements Stack<AnyType> {

    private MyArrayList<AnyType> list;
    private int theSize; // 其实不需要 theSize 更方便，但是这里还是要展示一下，在 MyLinkedListStack 中就没有 theSize

    public MyArrayListStack() {
        this.list = new MyArrayList<>();
        this.theSize = 0;
    }

    @Override
    public boolean push(AnyType x) {
        list.add(x);
        this.theSize++;
        return true;
    }

    @Override
    public AnyType pop() {
        AnyType oldVal = top();
        list.remove(list.size() - 1);
        theSize--;
        return oldVal;
    }

    @Override
    public AnyType top() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        AnyType topVal = list.get(list.size() - 1);
        return topVal;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public int size() {
        return theSize;
    }

    @Override
    public void clear() {
        theSize = 0;
        list.clear();
    }

    public static void main(String[] args) {
        Stack<Integer> stack = new MyArrayListStack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);

        System.out.println("栈顶的元素：" + stack.top());
        System.out.println("元素个数：" + stack.size());
        System.out.print("依次弹出所有元素：");
        while (!stack.isEmpty()) {
            System.out.print(stack.pop() + " ");
        }
        System.out.println();

        stack.push(6);
        stack.push(7);
        stack.push(8);
        System.out.println("重新 push 后，栈空吗：" + (stack.isEmpty() ? "空" : "不空"));
        stack.clear();
        System.out.println("调用 clear() 后，栈空吗：" + (stack.isEmpty() ? "空" : "不空"));
    }
}
