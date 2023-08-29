package com.mine.chapter03.practice;

import com.mine.chapter03.MyArrayListStack;
import com.mine.chapter03.Stack;

import java.util.EmptyStackException;

/**
 * @author CaoY
 * @date 2023-08-29 21:10
 * @description 练习 3.25
 * a. 提出一种数据结构支持栈 push 和 pop 操作，以及第三种操作 findMin，
 * 它返回该数据结构中的最小元素，所有操作均以 O(1) 最坏情形时间运行。
 *
 * b. 证明：如果我们添加找出并删除最小元素的第 4 种操作 deleteMin ，那么至少有一种操作必须花费 O(logN)
 *
 * 思路：
 * a. 参考答案的思路，就是使用两个栈 S 和 M，S 来实现 push 和 pop 的功能，M 来实现 findMin 的功能。
 *  我们设这个新的数据结构为 E。如果 E.push(x)，则 S.push(x) 若 x <= M.top()，则 M.push(x)；如果
 *  E.pop()，则 x = S.pop()，如果 x == M.top()，则 M.pop()，使得 M 中始终存着 push 过程中的最小
 *  元素，又由于栈的后进先出特性，如果弹出去最小的元素，则可以保证依次 push 的过程中最小的元素还在 M 中，进
 *  而实现这个功能。
 * b. 看题目说需要先看第 7 章，目前没有什么思路。todo 待做
 *
 * 注：感觉 a 挺有意思的，反正我第一遍看没有思路。
 */
public class Solution03_25 {

    public static void main(String[] args) {
        GoodStack<Integer> stack = new GoodStack<>();
        stack.push(2);
        stack.push(1);
        stack.push(3);
        stack.push(5);
        stack.push(0);
        stack.push(6);
        System.out.println("栈的元素个数：" + stack.size());
        System.out.println("当前栈的最小元素：" + stack.findMin());
        stack.pop();
        stack.pop();
        System.out.println("当前栈的最小元素：" + stack.findMin());
    }

    private static class GoodStack<AnyType extends Comparable<? super AnyType>> implements Stack<AnyType> {

        private MyArrayListStack<AnyType> S;
        private MyArrayListStack<AnyType> M;

        public GoodStack() {
            S = new MyArrayListStack<>();
            M = new MyArrayListStack<>();
        }

        @Override
        public boolean push(AnyType x) {
            if (x == null) {
                throw new IllegalArgumentException();
            }
            S.push(x);
            if (M.isEmpty() || x.compareTo(M.top()) <= 0) {
                M.push(x);
            }
            return true;
        }

        @Override
        public AnyType pop() {
            AnyType res = S.pop();
            if (!M.isEmpty() && res.compareTo(M.top()) == 0) {
                M.pop();
            }
            return res;
        }

        @Override
        public AnyType top() {
            return S.top();
        }

        @Override
        public boolean isEmpty() {
            return S.isEmpty();
        }

        @Override
        public int size() {
            return S.size();
        }

        @Override
        public void clear() {
            S.clear();
            M.clear();
        }

        public AnyType findMin() {
            if (this.isEmpty()) {
                throw new EmptyStackException();
            }

            return M.top();
        }
    }
}
