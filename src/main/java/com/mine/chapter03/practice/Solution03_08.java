package com.mine.chapter03.practice;

import java.util.*;

/**
 * @author CaoY
 * @date 2023-08-26 10:17
 * @description 练习 3.8 下面的 removeFirstHalf 方法删除传入的表的前半部分，回答下列问题：
 * a. 为什么在进入 for 循环前存储 theSize？
 * b. 如果 list 是一个 ArrayList，removeFirstHalf 方法的时间复杂度是多少？
 * c. 如果 list 是一个 LinkedList，removeFirstHalf 方法的时间复杂度是多少？
 * d. 对于这两种类型的 List 都是用迭代器都能使 removeFirstHalf 更快吗？
 *
 * 回答：
 *  a. 因为列表会随着 remove 而减小，如果不提前存储 theSize 值而是动态计算，则会导致没有删除够应该删除的元素。
 *  b. 如果 list 是一个 ArrayList，removeFirstHalf 方法的时间复杂度将是 O(n^2) 的，因为每次删除的都是
 *  第一个位置上的元素，这回导致其他位置上的元素都需要前移一位，因此时间复杂度是 O(n^2)。
 *  c. 如果 list 是一个 LinkedList，removeFirstHalf 方法的时间复杂度是 O(n)，因为每次都删除第一个
 *  位置上的元素，所以寻找元素的代价为 O(1)，而且链表删除不需要前移后面的元素，因此时间复杂度是 O(n)。
 *  d. 两种类型的 List 使用迭代器都不会使 removeFirstHalf 更快，因为总是第一个元素被删除，迭代器发挥
 *  其寻找元素的优势不明显。
 *
 * 启示：迭代器的主要优势是快速遍历查找元素。
 */
public class Solution03_08 {

    public static void main(String[] args) {
//        List<Integer> list = new ArrayList<>();
        List<Integer> list = new LinkedList<>();
        for (int i = 0; i < 10; i++) {
            list.add(i + 1);
        }
        // 注：这里有一个小坑，Arrays.asList(...) 得到的是一个数组的列表视图，不能对其进行
        // add 和 remove，否则会抛出 UnsupportedOperationException 异常
//        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7);
        System.out.println("删除前：" + list);
        removeFirstHalf(list);
        System.out.println("删除后：" + list);
    }

    private static void removeFirstHalf(List<?> list) {
        int theSize = list.size() / 2;

//        for (int i = 0; i < theSize; i++) {
//            list.remove(0);
//        }

        // 迭代器实现前半部分删除
        Iterator<?> iter = list.iterator();
        for (int i = 0; i < theSize; i++) {
            iter.next();
            iter.remove();
        }
    }
}
