package com.mine.chapter04.practice;

/**
 * @author CaoY
 * @date 2023-09-07 22:10
 * @description 练习 4.8
 * 给出对应图 4-71 中的前、中、后缀表达式
 *
 * 回答：其实就是这棵表达式树的前、中、后序三种遍历。
 * 前缀表达式：- * * a b + c d e；
 * 中缀表达式：a * b * (c + d) - e（原来是没有括号的，但是要考虑运算符的优先级）；
 * 后缀表达式：a b * c d + * e -。
 */
public class Solution04_08 {

    public static void main(String[] args) {
        System.out.println("简单的树的遍历问题。");
    }
}
