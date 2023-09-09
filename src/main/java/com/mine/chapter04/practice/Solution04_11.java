package com.mine.chapter04.practice;

import com.mine.chapter04.MyTreeSet;

import java.util.Iterator;

/**
 * @author CaoY
 * @date 2023-09-09 9:11
 * @description 练习 4.11
 * 编写 TreeSet 类的实现程序，其中相关的迭代器使用二叉查找树，在每个结点上添加一个指向其父结点的引用
 *
 * 使用 MyTreeSet 类
 */
public class Solution04_11 {

    public static void main(String[] args) {
        MyTreeSet<Integer> set = new MyTreeSet<>();
        set.insert(20);
        set.insert(40);
        set.insert(8);
        set.insert(15);
        set.insert(30);
        set.insert(31);
        set.insert(24);
        set.insert(7);
        set.allOrderShow("插入 20、40、8、15、30、31、24、7 后：");
        System.out.println("上述序列的最大值：" + set.findMax());
        System.out.println("上述序列的最小值：" + set.findMin());
//        testIteratorTraverse(set);
//        testIteratorRemove(set);
//        set.allOrderShow("删除元素后：");

        set.remove(3);
        set.remove(30);
        set.remove(20);
        set.remove(15);
        set.allOrderShow("删除 3、30、20、15 后：");

        System.out.println("set 非空吗：" + (set.isEmpty() ? "空" : "不空"));

        System.out.println("set 包含 30 吗：" + (set.contains(30) ? "包含" : "不包含"));
        System.out.println("set 包含 40 吗：" + (set.contains(40) ? "包含" : "不包含"));

        set.makeEmpty();
        // set.findMin(); // 会抛出 NoSuchElementException
        System.out.println("set 是否为空：" + (set.isEmpty() ? "空" : "不空"));
        System.out.println("=======================================");
        // set.insert(null); // 会跑出 IllegalArgumentException
        // testIteratorTraverse(set);
    }

    // 测试迭代器的遍历
    private static void testIteratorTraverse(MyTreeSet<Integer> set) {
        Iterator<Integer> iter = set.iterator();
        while (iter.hasNext()) {
            System.out.print(iter.next() + " ");
        }
        System.out.println();
    }

    // 测试迭代器的删除
    private static void testIteratorRemove(MyTreeSet<Integer> set) {
        System.out.print("删除所有奇数元素后：");
        Iterator<Integer> iter = set.iterator();
        while (iter.hasNext()) {
            int val = iter.next();
            if (val % 2 == 0) {// 这里可以通过改造条件删除奇数或偶数，以测试不同的删除情况
                iter.remove();
            }
        }

        iter = set.iterator();
        set.insert(1000);
        while (iter.hasNext()) {
            System.out.print(iter.next() + " ");
        }
        System.out.println();
    }
}
