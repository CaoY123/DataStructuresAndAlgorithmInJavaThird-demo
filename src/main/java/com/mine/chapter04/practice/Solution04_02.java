package com.mine.chapter04.practice;

/**
 * @author CaoY
 * @date 2023-09-07 21:19
 * @description 练习 4.2
 * 对于图 4-70 树中的每一个结点：
 *  a. 指出它的父结点。
 *  b. 列出它的儿子。
 *  c. 列出它的兄弟。
 *  d. 计算它的深度。
 *  e. 计算它的高度。
 *
 * 回答：
 *  a. 找出父结点：
 *      A：没有或者也可以说是 null；
 *      B、C：A；
 *      D、E：B；
 *      F：C；
 *      G、H：D；
 *      I、J：E；
 *      K：F；
 *      L、M：J。
 *  b. 找出儿子结点：
 *      A：B、C；
 *      B：D、E；
 *      C：F；
 *      D：G、H；
 *      E：I、J；
 *      F：K；
 *      G、H、I：null；
 *      J：L、M；
 *      K、L、M：null。
 *  c. 找出兄弟结点：
 *      A：null；
 *      B：C；
 *      C：B；
 *      D：E；
 *      E：D；
 *      F：null；
 *      G：H；
 *      H：G；
 *      I：J；
 *      J：I；
 *      K：null；
 *      L：M；
 *      M：L。
 *  d. 计算每个结点的深度：
 *      A：0；
 *      B、C：1；
 *      D、E、F：2；
 *      G、H、I、J、K：3；
 *      L、M：4。
 *  e. 计算每个结点的高度：
 *      A：4；
 *      B：3；
 *      C：2；
 *      D：1；
 *      E：2；
 *      F：1；
 *      G、H、I：0；
 *      J：1；
 *      K、L、M：0。
 *
 */
public class Solution04_02 {

    public static void main(String[] args) {
        System.out.println("Hello, Java World!");
    }
}
