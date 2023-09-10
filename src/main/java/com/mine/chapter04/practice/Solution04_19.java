package com.mine.chapter04.practice;

import com.mine.chapter04.AVLTree;

/**
 * @author CaoY
 * @date 2023-09-10 13:31
 * @description 练习 4.19
 * 指出将 2、1、4、5、9、3、6、7 插入到初始空 AVL 树的结果。
 *
 * 回答：因为无法直观地展示树，所以通过给出 AVL 树的前、中、后序的遍历结果来说明树的结构：
 *  前序遍历：4、2、1、3、6、5、9、7
 *  中序遍历：1、2、3、4、5、6、7、9
 *  后序遍历：1、3、2、5、7、9、6、4
 */
public class Solution04_19 {

    public static void main(String[] args) {
        // 使用 AVLTree 来验证一下
        AVLTree<Integer> tree = new AVLTree<>();
        tree.insert(2);
        tree.insert(1);
        tree.insert(4);
        tree.insert(5);
        tree.insert(9);
        tree.insert(3);
        tree.insert(6);
        tree.insert(7);
        tree.allOrderShow("插入 2、1、4、5、9、3、6、7 后：");
    }
}
