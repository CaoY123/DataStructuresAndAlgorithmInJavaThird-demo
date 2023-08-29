package com.mine.chapter03.practice;

/**
 * @author CaoY
 * @date 2023-08-29 22:37
 * @description 练习 3.27
 * 在 2.4 节用于计算斐波那契数的递归例程如果对 N = 50 运行，栈空间有可能用完吗？为什么？
 *
 * 回答：栈空间时有可能用完的，因为 2.4 节中的递归只有递归到最底层才能逐层返回结果，
 * 即释放栈中的元素，而在递归到最底层的值之前，栈空间有可能用完。
 *
 * 但是答案说：堆栈空间不会耗尽，因为只有49个调用将被堆叠。额，有道理，确实栈不会那么少，不过这种结构是
 * 不合理的，只要 N 再继续扩大，即使不会达到很大的程度，栈空间也会用完。
 */
public class Solution03_27 {

    public static void main(String[] args) {
        System.out.println("Hello, World!");
    }
}
