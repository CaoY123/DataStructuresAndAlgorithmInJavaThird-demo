package com.mine.chapter03.practice;

import com.mine.chapter03.Stack;

import java.util.EmptyStackException;

/**
 * @author CaoY
 * @date 2023-08-28 23:04
 * @description 练习 3.24 编写只用一个数组而实现两个栈的例程，
 * 这些例程都不应该声明溢出，除非数组中的每个单元都被使用。
 *
 * 如下：设计了一个 TwoStacks 类，内有一个数组，其中还有一个实现了 Stack 接口的内部类，为了实现
 * 题目的要求，设计第一个栈占据数组前半部分，第二个栈占据数组后半部分，且若其中一个栈的空间用尽（就是
 * 将前半部分或后半部分占满），则会将 TwoStacks 内部的数组动态扩容至两倍。建议使用的时候最好是两个
 * 栈一起用，否则会有大量的空间浪费，这个 TwoStacks 个人认为设计的还是不错的，尤其是扩容的操作，这
 * 样使用的时候不用顾忌太多，比较方便。
 *
 * 注：TwoStacks 未考虑并发相关的内容，下面的测试内容也比较有限，如有发现错误，敬请指正。
 */
public class Solution03_24 {

    public static void main(String[] args) {
        TwoStacks<Integer> twoStacks = new TwoStacks<>();
        Stack<Integer> firstStack = twoStacks.getFirstStack();
        Stack<Integer> secondStack = twoStacks.getSecondStack();
        System.out.println("第一个栈为空吗：" + (firstStack.isEmpty() ? "是" : "否"));
        System.out.println("第二个栈为空吗：" + (secondStack.isEmpty() ? "是" : "否"));

        for (int i = 0; i < 10; i++) {
            firstStack.push(i + 1);
            secondStack.push(i + 11);
        }

        for (int i = 0; i < 8; i++) {
            firstStack.pop();
            secondStack.pop();
        }

        firstStack.push(1000);
        secondStack.push(2000);

        System.out.println("出栈前的第一个栈的元素个数：" + firstStack.size());
        System.out.println("出栈前的第二个栈的元素个数： " + secondStack.size());
        System.out.println("第一个栈的栈顶元素：" + firstStack.top());
        System.out.println("第二个栈的栈顶元素：" + secondStack.top());

        show("第一个栈：", firstStack);
        show("第二个栈：", secondStack);

        System.out.println("出栈后的第一个栈的元素个数：" + firstStack.size());
        System.out.println("出栈后的第二个栈的元素个数： " + secondStack.size());
        System.out.println("**************************");

        for (int i = 0; i < 15; i++) {
            firstStack.push(i + 100);
            secondStack.push(i + 200);
        }

        System.out.println("出栈前，第一个栈的栈顶元素：" + firstStack.top());
        System.out.println("出栈前，第二个栈的栈顶元素：" + secondStack.top());

        show("添加 15 个元素后，第一个栈：", firstStack);
        show("添加 15 个元素后，第二个栈：", secondStack);
        System.out.println("出栈后的第一个栈的元素个数：" + firstStack.size());
        System.out.println("出栈后的第二个栈的元素个数： " + secondStack.size());
        System.out.println("**************************");

        for (int i = 0; i < 30; i++) {
            firstStack.push(101 + i);
            if (i < 16) {
                secondStack.push(201 + i);
            }
        }
        System.out.println("出栈前，第一个栈的栈顶元素：" + firstStack.top());
        System.out.println("出栈前，第二个栈的栈顶元素：" + secondStack.top());
        show("添加 30 个元素后，第一个栈：", firstStack);
        show("添加 16 个元素后，第二个栈：", secondStack);
        System.out.println("两个栈 clear 后：");
        firstStack.clear();
        secondStack.clear();
        System.out.println("第一个栈的元素个数：" + firstStack.size());
        System.out.println("第二个栈的元素个数：" + secondStack.size());
    }

    private static class TwoStacks<AnyType> {

        private AnyType[] theItems; // 中间的位置是前后两个栈的边界，不存储元素
        private final int DEFAULT_CAPACITY = 21;
        private int CURRENT_CAPACITY;
        private Stack<AnyType> firstStack;
        private Stack<AnyType> secondStack;

        public TwoStacks() {
            this.theItems = (AnyType[]) new Object[DEFAULT_CAPACITY];
            firstStack = new ArrayStack<>(0, 0, true, DEFAULT_CAPACITY / 2);
            secondStack = new ArrayStack<>(20, 20, false, DEFAULT_CAPACITY / 2);
            CURRENT_CAPACITY = DEFAULT_CAPACITY;
        }

        // 获取第一个栈
        public Stack<AnyType> getFirstStack() {
            return firstStack;
        }

        // 获取第二个栈
        public Stack<AnyType> getSecondStack() {
            return secondStack;
        }

        // 拷贝元素到新分配的空间以扩展栈
        private void enlargeCapacity(int capacity) {
            CURRENT_CAPACITY = capacity;
            AnyType[] tmpArray = (AnyType[]) new Object[CURRENT_CAPACITY];
            ArrayStack fs = (ArrayStack) this.firstStack;
            ArrayStack ss = (ArrayStack) this.secondStack;
            System.arraycopy(theItems, fs.beginIndex, tmpArray, 0, fs.size());
            System.arraycopy(theItems, ss.endIndex + 1, tmpArray, tmpArray.length - ss.size(), ss.size());
            theItems = tmpArray;
            fs.setCapacity(CURRENT_CAPACITY / 2);
            ss.setCapacity(CURRENT_CAPACITY / 2);

            int fsEndIndex = fs.size();
            fs.setBeginIndex(0);
            fs.setEndIndex(fsEndIndex);

            int ssEndIndex = CURRENT_CAPACITY - 1 - ss.size();
            ss.setBeginIndex(CURRENT_CAPACITY - 1);
            ss.setEndIndex(ssEndIndex);
        }

        // 基于数组实现的栈
        private class ArrayStack<T extends AnyType> implements Stack<AnyType> {

            private int beginIndex;
            private int endIndex;
            private boolean isFirst;
            private int capacity;

            public ArrayStack(int beginIndex, int endIndex, boolean isFirst, int capacity) {
                this.beginIndex = beginIndex;
                this.endIndex = endIndex;
                this.isFirst = isFirst;
                this.capacity = capacity;
            }

            public void setBeginIndex(int beginIndex) {
                this.beginIndex = beginIndex;
            }

            public void setEndIndex(int endIndex) {
                this.endIndex = endIndex;
            }

            public void setCapacity(int capacity) {
                this.capacity = capacity;
            }

            @Override
            public boolean push(AnyType x) {
                if (isFull()) {
                    // 必须动态扩容，不然不好用
                    enlargeCapacity((CURRENT_CAPACITY - 1) * 2 + 1);
                }
                if (isFirst) {
                    theItems[endIndex++] = x;
                } else {
                    theItems[endIndex--] = x;
                }
                return true;
            }

            @Override
            public AnyType pop() {
                if (isEmpty()) {
                    throw new EmptyStackException();
                }
                if (isFirst) {
                    return theItems[--endIndex];
                }

                return theItems[++endIndex];
            }

            @Override
            public AnyType top() {
                if (isEmpty()) {
                    throw new EmptyStackException();
                }

                if (isFirst) {
                    return theItems[endIndex - 1];
                }

                return theItems[endIndex + 1];
            }

            @Override
            public boolean isEmpty() {
                return beginIndex == endIndex;
            }

            @Override
            public int size() {
                return Math.abs(beginIndex - endIndex);
            }

            @Override
            public void clear() {
                if (isFirst) {
                    beginIndex = endIndex= 0;
                } else {
                    beginIndex = endIndex = theItems.length - 1;
                }
            }

            private boolean isFull() {
                return size() == capacity;
            }
        }
    }

    // 用于展示栈的方法，辅助测试时展示
    private static <AnyType> void show(String des, Stack<AnyType> stack) {
        System.out.print(des);
        while (!stack.isEmpty()) {
            System.out.print(stack.pop() + " ");
        }
        System.out.println();
    }
}
