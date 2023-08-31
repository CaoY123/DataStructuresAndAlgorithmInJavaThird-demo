package com.mine.chapter03.practice;

/**
 * @author CaoY
 * @date 2023-08-31 21:26
 * @description 练习 3.36
 * 设我们有到单链表的一个结点的引用，而且保证它不是该表的最后的结点。我们没有任何其他结点的引用（除非
 * 通过后面的一些链）。描述一个 O(1) 算法，该算法逻辑上从该链表删除存储在这样一个结点上的值，同时保
 * 持链表的完整性。（提示：涉及下一个结点）
 *
 * 答案：先将当前结点的下一个结点的值拷贝到当前结点上，然后利用当前结点删除下一个结点。
 *
 * 评价：这是答案的解法，太巧妙了，学到了，思维好开阔，你就说删没删吧？（doge）
 *
 * 注：这里不作实现了...
 */
public class Solution03_36 {

    public static void main(String[] args) {
        System.out.println("Hello, Java World!");
    }
}
