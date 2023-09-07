package com.mine.chapter04.practice;

import com.mine.chapter04.BinarySearchTree;

/**
 * @author CaoY
 * @date 2023-09-07 22:17
 * @description 练习 4.9
 * a. 指出将 3、1、4、6、9、2、5、7 插入到初始为空的二叉查找树的结果。
 * b. 指出删除根后的结果。
 *
 * 回答：因为我没有办法在这上面展示图像，所以我会通过分别展示前、中、后序的遍历结果来说明树的情况。
 *  a. 前序遍历：3、1、2、4、6、5、9、7
 *     中序遍历：1、2、3、4、5、6、7、9
 *     后序遍历：2、1、5、7、9、6、4、3
 *
 *  b. 前序遍历：4、1、2、6、5、9、7
 *     中序遍历：1、2、4、5、6、7、9
 *     后序遍历：2、1、5、7、9、6、4
 */
public class Solution04_09 {
    // 用 BinarySearchTree 验证一下，还发现了一个 remove 方法的 bug，改正了。
    public static void main(String[] args) {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        tree.insert(3);
        tree.insert(1);
        tree.insert(4);
        tree.insert(6);
        tree.insert(9);
        tree.insert(2);
        tree.insert(5);
        tree.insert(7);
        tree.allOrderShow("第一次插入 8 个元素后：");
        tree.remove(3);
        tree.allOrderShow("删除根元素后：");
    }
}
