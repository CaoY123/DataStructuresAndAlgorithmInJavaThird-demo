package com.mine.chapter03.practice;

import java.util.*;

/**
 * @author CaoY
 * @date 2023-08-24 21:59
 * @description 练习 3.4
 *
 * 注：
 *  1. 通配符只不过是用来修饰类型的，如果实在不理解就暂时先别管它，其实解读时候逐词拆解即可。
 *  2. chat-gpt3.5 对这个 <AnyType extends Comparable<? super AnyType>> 的解读如下：
 *      1）<AnyType>: 这是一个泛型类型参数的名称，
 *      它表示这个方法可以接受任何类型的参数，这个类型将在方法内部使用。
 *      2）extends Comparable<? super AnyType>: 这部分的含义是限制泛型类型
 *      参数 AnyType 必须是实现了 Comparable 接口的类型，这意味着这些类型可以进行比较操作。
 *      Comparable 接口中的 compareTo 方法允许对象之间的比较，以便进行排序和其他操作。
 *      3）<? super AnyType>: 这部分是为了在限定类型参数的上界时使用的。
 *      super AnyType 表示可以是 AnyType 的超类，或者 AnyType 自身。这是为了使方法更加灵活，
 *      可以接受比 AnyType 更一般的类型作为参数。
 *      4）为什么要使用 super？这涉及到 Java 泛型中的协变和逆变的概念。
 *      在泛型中，extends 表示协变（covariant），允许更具体的类型传递给方法，
 *      而 super 表示逆变（contravariant），允许更一般的类型传递给方法。
 *  3. 答案的想法和笔者的大同小异，只是细节策略不同，笔者觉得自己的写法看起来更巧妙一些但是其中的 while
 *  的条件判断有点复杂，请酌情选择。同时，答案为我补充了 <? super AnyType> 通配类型细节，
 *  使代码具通用性，感谢它。
 *
 *  4. 迭代继续判断条件：!(!iter1.hasNext() && flag != 2) && !(!iter2.hasNext() && flag != 1)
 *  看起来比较复杂，实际上这正是为了适应我的这种结构，因为迭代的条件不止取决于迭代器的位置，还取决于下一次迭代
 *  需不需要它向前走，这是由 flag 判断的，所以上述的 !iter1.hasNext() && flag != 2 的意思是：如果这个
 *  迭代器没有下一个元素（走到了末尾）并且下一次还需要它继续向前迭代（flag 为 0 和 1 时均需要 iter1 往前走），
 *  那么就终止循环，而要继续循环则取反并且同时也要按照同样的道理检验 iter2。这个由 Solution03_05 启发得到，
 *  不这么设置条件在此种结构下会漏掉表边缘相同的元素。
 */
public class Solution03_04 {

    public static void main(String[] args) {
        List<Integer> L1 = Arrays.asList(1, 3, 4, 5, 7, 9);
        List<Integer> L2 = Arrays.asList(2, 4, 6, 7, 8);
        List<Integer> res = intersection(L1, L2);
        System.out.println("L1 为：" + L1);
        System.out.println("L2 为：" + L2);
        System.out.println("L1 和 L2 交集为：" + res);
        System.out.println("**************************************");
        L1 = Arrays.asList(1, 3, 5, 7, 9);
        L2 = Arrays.asList(2, 4, 6, 8, 10);
        res = intersection(L1, L2);
        System.out.println("L1 为：" + L1);
        System.out.println("L2 为：" + L2);
        System.out.println("L1 和 L2 交集为：" + res);
        System.out.println("**************************************");
        L1 = Arrays.asList(6);
        L2 = Arrays.asList(6, 9);
        res = intersection(L1, L2);
        System.out.println("L1 为：" + L1);
        System.out.println("L2 为：" + L2);
        System.out.println("L1 和 L2 交集为：" + res);
        System.out.println("**************************************");
        L1 = null;
        L2 = Arrays.asList(2);
        res = intersection(L1, L2);
        System.out.println("L1 为：" + L1);
        System.out.println("L2 为：" + L2);
        System.out.println("L1 和 L2 交集为：" + res);
        System.out.println("**************************************");
        L1 = Arrays.asList(1, 3, 5, 7, 9, 10);
        L2 = Arrays.asList(2, 4, 6, 8, 10);
        res = intersection(L1, L2);
        System.out.println("L1 为：" + L1);
        System.out.println("L2 为：" + L2);
        System.out.println("L1 和 L2 交集为：" + res);
    }

    private static <AnyType extends Comparable<? super AnyType>>
    List<AnyType> intersection(List<AnyType> L1, List<AnyType> L2) {
        // L1、L2的非空校验
        if (L1 == null || L1.size() == 0 || L2 == null || L2.size() == 0) {
            return new ArrayList<>();
        }

        List<AnyType> result = new ArrayList<>();
        Iterator<AnyType> iter1 = L1.iterator();
        Iterator<AnyType> iter2 = L2.iterator();
        int flag = 0; // 需要哪个迭代器移动的标记。值为0，都移动；为1，第一个移动；为2，第二个移动
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
            if (Objects.equals(val1, val2)) {
                // 这里考虑 val1 和 val2 可能为 null，但是因为 L1 和 L2是已经排好序的，
                // 而对 null 值排序并没有什么实际意义，所以下面也不考虑 null 值的出现
                result.add(val1);
                flag = 0;
            } else {
                if (val1.compareTo(val2) < 0) {
                    flag = 1;
                } else {
                    flag = 2;
                }
            }
        }
        return result;
    }
}
