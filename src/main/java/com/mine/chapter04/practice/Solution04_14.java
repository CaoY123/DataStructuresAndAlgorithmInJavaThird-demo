package com.mine.chapter04.practice;

/**
 * @author CaoY
 * @date 2023-09-10 3:13
 * @description 练习 4.14
 *
 * 回答：抄答案
 *  a. 保留一个位数组 b，存储已经插入树中的元素，若新生成的元素不在这个数组 b 中，则可以插入；反之，则不能插入。
 *  其中，我们总共需要生成 M 个元素，又已知 b 中有 N 个元素，则生成可以插入的元素的可能性是 (M - N) / M，
 *  则这个操作的运行时间（也就是试验的运行次数）为： M / (M - N) = a / (a - 1)。
 *  b. 要在树中找到一个元素，重复生成随机整数，直到一个已经使用的整数出现发现。找到一个的概率是N/M，则操作的
 *  运行时间（就是试验的运行次数）为：a。
 *  c. a 相当于计算的是 insert 的效率，b 相当于计算的是 remove 的效率，要尽量实现两种操作的效率平衡，
 *  则令：a = a / (a - 1)（a != 0），求解这个方程，得 a = 2。
 */
public class Solution04_14 {

    public static void main(String[] args) {
        System.out.println("树真麻烦啊！");
    }
}
