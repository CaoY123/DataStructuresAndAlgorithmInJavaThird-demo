package com.mine.chapter04.practice;

/**
 * @author CaoY
 * @date 2023-09-10 11:03
 * @description 练习 4.16
 * 重做二叉查找树类以实现懒惰删除。仔细注意这将影响所有的例程，特别具有挑战性的是 findMin 和 findMax，
 * 它们现在必须递归的完成。
 *
 * 回答：可以采用往二叉查找树的结点类增加一个 isRemoved（boolean）字段，若为 true，则已经删除，反之，则没
 * 有删除。每次在对于删除的结点将其设置为 true，同时在树中记录结点的总数量和已经删除的数量，当删除的数量达到一
 * 定比例时，对已经删除的几点进行真删除。同时对于像 findMin 和 findMax 这样的例程，每次通过 isRemoved 字
 * 段的值来判断当前结点的值是否有效。
 *
 * 注：有限的修改，这里不做具体实现了。当然如果有更好的实现策略，欢迎补充！
 */
public class Solution04_16 {

    public static void main(String[] args) {
        System.out.println("懒得再写一个涉及整棵树的数据结构了！");
    }
}
