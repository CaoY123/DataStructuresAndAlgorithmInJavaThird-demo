package com.mine.chapter04.practice;

/**
 * @author CaoY
 * @date 2023-09-07 21:47
 * @description 满结点是具有两个儿子的结点，证明满结点的个数加 1 等于非空二叉树的树叶的个数。（比较重要的结论）
 *
 * 回答：已知结点个数为 N，每个结点有两条链，那么总链数就是 2N，我们由练习 4.4 的结论可以知道指向 null
 * 的链路数为 N + 1，那么指向非空的链路数为 2N - (N + 1) = N - 1。
 * 再设满结点的个数为 F，叶子结点的个数为 L，只有一个子结点的结点个数为 H。
 * 那么有：F + L + H = N（意义：所有结点都可以分为这三类结点）... (1)
 * 还有：2F + H = N - 1（意义：2F + H 计算的是指向非 null 的链路数）... (2)
 * 由 (1) - (2) 得：L - F = 1，即证得结论。
 */
public class Solution04_06 {

    public static void main(String[] args) {
        System.out.println("不难，但是我证明的时候还是欠缺思路。");
    }
}
