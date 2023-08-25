package com.mine.chapter03.practice;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

/**
 * @author CaoY
 * @date 2023-08-25 19:53
 * @description 练习 3.6 约瑟夫环问题
 * 这个练习题本质上描述的是约瑟夫环问题，但是它描述的有点不准确，约瑟夫环问题是不从 0 开始报数的，
 * 而是从 1 开始报数的，所以笔者特在此展示较为经典的约瑟夫环问题的描述：
 *  有 n 个人（编号从 1 到 n）围坐在一圈。从编号为 1 的人开始，沿着顺时针方向报数，
 *  每报到某个固定的数 m 时，报数为 m 的人将被移出圈外，剩下的人继续从 1 开始重新报数。
 *  被移出圈外的人不再参与报数。游戏继续进行，直到只剩下最后一个人。需要找出最后剩下的人的编号。
 *
 *  注：本题若用 List 实现，对于迭代器的使用的练习还是很有意义的，可以深化对它的理解。
 */
public class Solution03_06 {

    public static void main(String[] args) {
        int N = 7;
        int M = 3;
//        List<Integer> result = josephRing(N, M);
        List<Integer> result = josephRingOpt(N, M);
        System.out.println("N = " + N + "、M = " + M + " 时，依次淘汰的序列为：" + result);
        System.out.println("**********************************");
        N = 10;
        M = 2;
//        result = josephRing(N, M);
        result = josephRingOpt(N, M);
        System.out.println("N = " + N + "、M = " + M + " 时，依次淘汰的序列为：" + result);
        System.out.println("**********************************");
        N = 5;
        M = 3;
//        result = josephRing(N, M);
        result = josephRingOpt(N, M);
        System.out.println("N = " + N + "、M = " + M + " 时，依次淘汰的序列为：" + result);
        System.out.println("**********************************");
        N = 8;
        M = 4;
//        result = josephRing(N, M);
        result = josephRingOpt(N, M);
        System.out.println("N = " + N + "、M = " + M + " 时，依次淘汰的序列为：" + result);
        System.out.println("**********************************");
        N = 6;
        M = 1;
//        result = josephRing(N, M);
        result = josephRingOpt(N, M);
        System.out.println("N = " + N + "、M = " + M + " 时，依次淘汰的序列为：" + result);
        System.out.println("**********************************");
        N = 5;
        M = 5;
//        result = josephRing(N, M);
        result = josephRingOpt(N, M);
        System.out.println("N = " + N + "、M = " + M + " 时，依次淘汰的序列为：" + result);
        System.out.println("**********************************");
    }

    /**
     * 求解约瑟夫环问题（需要保证 M 和 N 的取值的正确性）
     * @param N 参加游戏的总人数，同时也表示这些人的编号为 1 - N。N > 1
     * @param M 表示传递 M 次后轮到的人离开。1 <= M <= N
     * @return  游戏人依序离开的编号列表，也包括最后留下的胜利者，处于表的最后一个位置上
     */
    private static List<Integer> josephRing(int N, int M) {
        List<Integer> list = new LinkedList<>();
        for (int i = 1; i <= N; i++) {
            list.add(i);
        }

        List<Integer> result = new ArrayList<>();
        ListIterator<Integer> iter = list.listIterator();
        int index = 0;
        while (!list.isEmpty()) {
            if (!iter.hasNext()) {
                iter = list.listIterator();
            }
            int val = iter.next();
            index = (index + 1) % M;
            if (index == 0) {
                result.add(val);
                iter.remove();
            }
        }

        return result;
    }

    /**
     * josephRing 的优化方法
     * 两个优化点：
     *  1. 当 M 很大时，为了避免迭代 M 步，可以迭代 M % N 步，其中 N 为剩余的游戏者人数；
     *  2. 还是假设 N 为剩余的游戏者人数，当 M % N 计算得的 m 大于等于 (N + 1) / 2 时，
     *  就可以使用 previous 向前迭代，以缩短迭代的步数
     * @param N 参加游戏的总人数，同时也表示这些人的编号为 1 - N。N > 1
     * @param M 表示传递 M 次后轮到的人离开。1 <= M <= N
     * @return  游戏人依序离开的编号列表，也包括最后留下的胜利者，处于表的最后一个位置上
     */
    private static List<Integer> josephRingOpt(int N, int M) {
        List<Integer> list = new LinkedList<>();
        for (int i = 1; i <= N; i++) {
            list.add(i);
        }

        List<Integer> result = new ArrayList<>();
        ListIterator<Integer> iter = list.listIterator();
        int m = M % list.size();
        int val = 0;
        while (!list.isEmpty()) {
            if (m == 0) {
                // m 等于 0 为特殊情况，此时按理来说直接取迭代器前一个即可（注意这里认为容器是一个循环结构）
                // 但是基于迭代器的“只有迭代过才能使用”的特性，需要做特殊处理
                if (!iter.hasPrevious()) {
                    iter = list.listIterator(list.size());
                }
                val = iter.previous();
            } else if (m <= (list.size() + 1) / 2) {
                // 移动到目标排除点距离小于等于当前人数的一半，迭代器用 next 迭代
                if (!iter.hasNext()) {
                    iter = list.listIterator();
                }
                for (int i = 0; i < m; i++) {
                    if (!iter.hasNext()) {
                        iter = list.listIterator();
                    }
                    val = iter.next();
                }
            } else {
                // 移动到目标排除点距离大于当前人数的一半，迭代器用 previous 迭代
                if (!iter.hasPrevious()) {
                    iter = list.listIterator(list.size());
                }
                for (int i = 0; i < list.size() + 1 - m; i++) {
                    if (!iter.hasPrevious()) {
                        iter = list.listIterator(list.size());
                    }
                    val = iter.previous();
                }
            }

            result.add(val);
            iter.remove();
            if (!list.isEmpty()) { // 防止除数为 0
                m = M % list.size();
            }
        }

        return result;
    }
}
