package com.mine.chapter03.practice;

import java.util.*;

/**
 * @author CaoY
 * @date 2023-08-24 22:51
 * @description 练习 3.5
 *
 * 注意：
 *  1. 迭代继续判断条件：!(!iter1.hasNext() && flag != 2) && !(!iter2.hasNext() && flag != 1)
 *  看起来比较复杂，实际上这正是为了适应我的这种结构，因为迭代的条件不止取决于迭代器的位置，还取决于下一次迭代
 *  需不需要它向前走，这是由 flag 判断的，所以上述的 !iter1.hasNext() && flag != 2 的意思是：如果这个
 *  迭代器没有下一个元素（走到了末尾）并且下一次还需要它继续向前迭代（flag 为 0 和 1 时均需要 iter1 往前走），
 *  那么就终止循环，而要继续循环则取反并且同时也要按照同样的道理检验 iter2。
 *  2. 这个 BUG 启发我修改了 Solution03_04 的判断条件，并作出了修改，这是为了让我不要漏掉边缘的元素。
 *  3. 照抄一下答案的解法，写得更加优雅巧妙，循环判断条件更加简单，其用临时变量作为循环条件的参数，能
 *  避免由于迭代器的 next 带来的动态干扰（因为会涉及到早就移动到了最后一个位置）
 */
public class Solution03_05 {

    public static void main(String[] args) {
        List<Integer> L1 = Arrays.asList(1, 3, 4, 5, 7, 9);
        List<Integer> L2 = Arrays.asList(2, 4, 6, 7, 8);
//        List<Integer> res = union(L1, L2);
        List<Integer> res = union2(L1, L2);
        System.out.println("L1 为：" + L1);
        System.out.println("L2 为：" + L2);
        System.out.println("L1 和 L2 并集为：" + res);
        System.out.println("**************************************");
        L1 = Arrays.asList(1, 3, 5, 7, 9);
        L2 = Arrays.asList(2, 4, 6, 8, 10);
//        res = union(L1, L2);
        res = union2(L1, L2);
        System.out.println("L1 为：" + L1);
        System.out.println("L2 为：" + L2);
        System.out.println("L1 和 L2 并集为：" + res);
        System.out.println("**************************************");
        L1 = Arrays.asList(6);
        L2 = Arrays.asList(6, 9);
//        res = union(L1, L2);
        res = union2(L1, L2);
        System.out.println("L1 为：" + L1);
        System.out.println("L2 为：" + L2);
        System.out.println("L1 和 L2 并集为：" + res);
        System.out.println("**************************************");
        L1 = null;
        L2 = Arrays.asList(2);
//        res = union(L1, L2);
        res = union2(L1, L2);
        System.out.println("L1 为：" + L1);
        System.out.println("L2 为：" + L2);
        System.out.println("L1 和 L2 并集为：" + res);
        System.out.println("**************************************");
        L1 = Arrays.asList(6, 7, 9);
        L2 = Arrays.asList(6, 9);
//        res = union(L1, L2);
        res = union2(L1, L2);
        System.out.println("L1 为：" + L1);
        System.out.println("L2 为：" + L2);
        System.out.println("L1 和 L2 并集为：" + res);
    }

    // L1 和 L2 为已经从小到大排序过的表
    private static <AnyType extends Comparable<? super AnyType>>
    List<AnyType> union(List<AnyType> L1, List<AnyType> L2) {
        // L1 和 L2 为空的处理
        if (L1 == null || L1.size() == 0) {
            if (L2 == null) {
                return new ArrayList<>();
            }
            return new ArrayList<>(L2);
        } else if (L2 == null || L2.size() == 0) {
            return new ArrayList<>(L1);
        }

        List<AnyType> result = new ArrayList<>();
        ListIterator<AnyType> iter1 = L1.listIterator();
        ListIterator<AnyType> iter2 = L2.listIterator();
        int flag = 0;
        AnyType val1 = null;
        AnyType val2 = null;
        while (!(!iter1.hasNext() && flag != 2) &&
                !(!iter2.hasNext() && flag != 1)) {
            switch (flag) {
                case 0:
                    val1 = iter1.next();
                    val2 = iter2.next();
                    break;
                case 1:
                    val1 = iter1.next();
                    break;
                case 2:
                    val2 = iter2.next();
                    break;
            }

            if (val1.equals(val2)) {
                result.add(val1);
                flag = 0;
            } else {
                if (val1.compareTo(val2) < 0) {
                    result.add(val1);
                    flag = 1;
                } else {
                    result.add(val2);
                    flag = 2;
                }
            }
        }

        ListIterator<AnyType> iter = null;
        if (flag > 0) {
            iter = flag == 1 ? iter2 : iter1;
            iter.previous();
        } else {
            iter = iter1.hasNext() ? iter1 : iter2;
        }
        while (iter.hasNext()) {
            result.add(iter.next());
        }

        return result;
    }

    // 使用答案的写法（参数条件与 union 方法相同）
    private static <AnyType extends Comparable<? super AnyType>>
    List<AnyType> union2(List<AnyType> L1, List<AnyType> L2) {
        // L1 和 L2 为空的处理
        if (L1 == null || L1.size() == 0) {
            if (L2 == null) {
                return new ArrayList<>();
            }
            return new ArrayList<>(L2);
        } else if (L2 == null || L2.size() == 0) {
            return new ArrayList<>(L1);
        }

        List<AnyType> result = new ArrayList<>();
        Iterator<AnyType> iter1 = L1.iterator();
        Iterator<AnyType> iter2 = L2.iterator();

        AnyType val1 = null;
        AnyType val2 = null;
        if (iter1.hasNext() && iter2.hasNext()) {
            val1 = iter1.next();
            val2 = iter2.next();
        }

        while (val1 != null && val2 != null) {
            int compareRes = val1.compareTo(val2);
            if (compareRes == 0) {
                result.add(val1);
                val1 = iter1.hasNext() ? iter1.next() : null;
                val2 = iter2.hasNext() ? iter2.next() : null;
            } else if (compareRes > 0) {
                result.add(val2);
                val2 = iter2.hasNext() ? iter2.next() : null;
            } else {
                result.add(val1);
                val1 = iter1.hasNext() ? iter1.next() : null;
            }
        }

        // 可能会漏掉某个表的最后一个元素
        Iterator<AnyType> iter = val1 == null ? iter2 : iter1;
        AnyType val = val1 == null ? val2 : val1;
        if (val != null) {
            result.add(val);
        }
        while (iter.hasNext()) {
            result.add(iter.next());
        }

        return result;
    }
}
