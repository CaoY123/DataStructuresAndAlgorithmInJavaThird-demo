package com.mine.chapter03.practice;

/**
 * @author CaoY
 * @date 2023-08-31 21:20
 * @description 练习 3.36 循环单链表实现队列类
 *
 * 回答：为了使所有基本操作都可以使常数最坏时间执行，应该选择 b，让迭代器指向表的最后一项，因为是循环单链表，
 * 即表的最后一项元素的 next 指向首元素，那么可以在常数时间内使用这个指向尾元素的迭代器获得首元素，但是指向
 * 首元素的迭代器要想获得尾元素却需要遍历 N 个元素，这显然对于队列的 enQueue 操作很棘手，因为这超出了常数
 * 的时间复杂度。
 *
 * 注：这个很简单，不作实现了，如想实现参照上述说明即可。
 */
public class Solution03_35 {

    public static void main(String[] args) {
        System.out.println("Hello, World!");
    }
}
