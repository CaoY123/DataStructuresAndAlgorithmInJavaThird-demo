package com.mine.chapter03;

import java.util.EmptyStackException;

/**
 * @author CaoY
 * @date 2023-08-22 23:17
 * @description 使用自定义的 MyLinkedList 实现栈
 *
 * 一点思考：其实可以定义一个公共的 MyList 接口，然后让 MyArrayList 和 MyLinkedList 都去继承这个接口
 * 然后在栈中定义 MyList 类型的表，然后在栈的具9体的构造器中传入不同的 MyList 对象即可，不过这里就不作变更了
 */
public class MyLinkedListStack<AnyType> implements Stack<AnyType>{

    private MyLinkedList<AnyType> list;

    public MyLinkedListStack() {
        list = new MyLinkedList<>();
    }

    @Override
    public boolean push(AnyType x) {
        list.add(x);
        return true;
    }

    @Override
    public AnyType pop() {
        AnyType oldVal = top();
        list.remove(list.size() - 1);
        return oldVal;
    }

    @Override
    public AnyType top() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return list.get(list.size() - 1);
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public void clear() {
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
