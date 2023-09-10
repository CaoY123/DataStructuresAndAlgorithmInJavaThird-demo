package com.mine.chapter04.practice;

/**
 * @author CaoY
 * @date 2023-09-10 14:07
 * @description 练习 4.20
 *
 * 回答：所谓”理想的平衡二叉树“，就是满二叉查找树，这个的数学证明可直接参考答案，笔者通过不完全归纳法，
 * 令 N = 4,，即依次插入 15 个结点时试了一下这个 insert 的流程，所有的平衡都是“右右”引起的，因此
 * 右旋即可。在插入最后一个结点并调整平衡后，总会行成一棵满二叉树。读者可以自己试试，这里不作数学证明了。
 */
public class Solution04_20 {

    public static void main(String[] args) {
        System.out.println("这个升序插入的序列其中的不平衡总是\'右右\'引起的，需要右旋。");
    }
}
