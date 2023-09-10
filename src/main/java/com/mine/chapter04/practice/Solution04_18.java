package com.mine.chapter04.practice;

/**
 * @author CaoY
 * @date 2023-09-10 11:17
 * @description 练习 4.18
 * a. 给出高度为 h 的 AVL 树的结点的最少个数额精确表达式。
 * b. 给出高度为 15 的 AVL 树中结点的最小个数为多少？
 *
 * 回答：
 *  a. N(0) = 1, N(1) = 2, N(h) = N(h − 1) + N(h − 2) + 1。
 *  b. 就是个递归式，依据上式往出推即可，实际上类似于一个斐波那契数列。
 */
public class Solution04_18 {

    public static void main(String[] args) {
        System.out.println("Hello, World!");
    }
}
