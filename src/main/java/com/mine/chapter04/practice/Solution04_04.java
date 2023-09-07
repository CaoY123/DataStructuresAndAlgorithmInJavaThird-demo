package com.mine.chapter04.practice;

/**
 * @author CaoY
 * @date 2023-09-07 21:37
 * @description 练习 4.4
 * 证明在 N 个结点的二叉树中，存在 N + 1 个 null 链，代表 N + 1 个儿子。
 *
 * 回答：因为有 N 个结点，每个结点都有两条链路，所以总共有 2N 条链路。但是除了根结点外，每个结点
 * 都有一条来自于其父结点的链路，所以指向 null 的链路的个数为：2N - （N - 1） = N + 1。即证明
 * 上述结论。
 */
public class Solution04_04 {

    public static void main(String[] args) {
        System.out.println("其实也不难，但我没想到(doge)！");
    }
}
