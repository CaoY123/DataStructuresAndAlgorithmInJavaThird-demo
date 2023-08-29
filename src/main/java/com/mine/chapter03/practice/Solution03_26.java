package com.mine.chapter03.practice;

/**
 * @author CaoY
 * @date 2023-08-29 22:27
 * @description 练习 3.26 指出如何用一个数组实现三个栈的结构
 *
 * 可以参考 Solution03_24 类，这是用一个数组实现两个栈，类似地，也可以来实现三个栈，只不过需要标记两个边界罢了，
 * 对于三个栈的 beginIndex，都是位于各自下标区间的靠左部分，然后 push 时依次向右扩展。
 *
 * 但是我还想到一个策略，就是三个栈的 beginIndex 在开始时都挨着，只不过 push 时 endIndex 增加 3，
 * 然后增长的极限的 endIndex 也挨着，就是一种间隔增长的思想。
 *
 * 注：本问题代码不作实现。
 */
public class Solution03_26 {

    public static void main(String[] args) {
        System.out.println("Hello, World!");
    }
}
