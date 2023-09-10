package com.mine.chapter04.practice;

/**
 * @author CaoY
 * @date 2023-09-10 20:34
 * @description 练习 4.26
 * 写出执行双旋转的方法，其效率要超过两个单旋转。
 *
 * 回答：在下面的注释部分
 */
public class Solution04_26 {

    public static void main(String[] args) {
        System.out.println("这个实现双旋转的想法确实不错，够直接，但是答案应该最后返回错了。");
    }

    // 直接贴答案了，但是答案里返回的是 k3，我觉得应该返回 k2，因为把 k2 转成父结点了。
    // 下面的代码其实就是直接连接相应的子树部分，具体理解可参照书上 P89 图 4-35
//    static AvlNode doubleRotateWithLeft( AvlNode k3 ) {
//        AvlNode k1, k2;
//        k1 = k3.left;
//        k2 = k1.right;
//        k1.right = k2.left;
//        k3.left = k2.right;
//        k2.left = k1;
//        k2.right = k3;
//        k1.height = max( height(k1.left), height(k1.right) ) + 1;
//        k3.height = max( height(k3.left), height(k3.right) ) + 1;
//        k2.height = max( k1. height, k3.height ) + 1;
//        return k2;
//    }

}
