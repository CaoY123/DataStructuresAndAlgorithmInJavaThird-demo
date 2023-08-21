package com.mine.chapter01;

import com.mine.util.RandomUtil;

import java.util.*;

/**
 * @author CaoY
 * @date 2023-08-20 11:33
 * @description 编写一个程序解决选择问题。令 k = N/2。画出表格显示程序对于 N 种不同的值的运行时间
 * 选择问题：一组 N 个数而要确定其中第 k 个最大者。
 *
 * 输入限定：N > 1 且 N <= 2147483647（k > 0），传入的数组一定有元素
 *
 * 目前所知的就是两个思路：
 * 1. 对整个数组进行降序排序，然后再取第 k - 1 个位置上的元素；
 * 2. 对前 k 个元素进行降序排序，之后再遍历剩下的元素，依次将其加入到已排序的数组中，最后取已排序部分的最后一个元素即可
 *  注：这里使用了迭代器(listIterator)来删除和添加元素，注意其使用，next() 和 previous() 有移动的意思，
 *  甚至会体现在 debug 中，从而影响代码运行，这点注意。
 * 3. todo 还有更好的解法，待补充
 *
 * 注：
 *  1. 这里主要是实现代码，就不画图了
 *  2. 上面的两种方法都不太好，它们均不能适应规模较大的数据输入，看书上说还有更快的，能适应超大规模
 *  的数据输入，待笔者后续补充。
 */
public class Solution01_01 {
    public static void main(String[] args) {
//        int[] arr = new int[]{3, 2, 8, 12, 6, 1, 5};
//        int[] arr = new int[]{3, 2, 8, 12, 6, 1, 5, 30, 50 ,23, 76, 55, -1, -4, 30, 62};
//        int[] arr = new int[]{98, 83, 80, 68, 59, 53, 45, 40, 34, 29};
//        int[] arr = new int[]{30, 25, 60, 24, 19, 61}; // 替换已排序部分的最大值
        int[] arr = new int[]{30, 25, 60, 24, 19, 26}; // 替换已排序部分的最后一个数
        // 用来显示排序后效果的数组
        int[] newArr = Arrays.copyOf(arr, arr.length);
        System.out.println("原数组：" + Arrays.toString(arr));
        bubbleSort(newArr, 0, newArr.length);
        System.out.println("整体排序后：" + Arrays.toString(newArr));
        int k = arr.length / 2;
        System.out.println("第" + k + "个最大的值：" + func(arr, k));
        System.out.println("================================================");

        int[] arr2 = RandomUtil.randomIntArrays(-100, 987, 30);
        System.out.println("随机生成的数组：" + Arrays.toString(arr2));
        int[] newArr2 = Arrays.copyOf(arr2, arr2.length);
        bubbleSort(newArr2, 0, newArr2.length);
        System.out.println("整体排序后：" + Arrays.toString(newArr2));
        k = arr2.length / 2;
        System.out.println("第" + k + "个最大的值：" + func(arr2, k));
    }

    // 思路2的实现，毫无疑问，这种方法会破坏原数组，在是解决类似的实际问题的时候，
    // 要考虑是否需要保护性拷贝，不过这不是我们算法该关心的问题
    private static int func(int[] arr, int k) {
        int N = arr.length;
        bubbleSort(arr, 0, k);
        List<Integer> list = new LinkedList<>();
        for (int i = 0; i < k; i++) {
            list.add(arr[i]);
        }

        // 这里的若用数组的数据移动非常拉胯，很费时间，所以我选择使用 LinkedList
        // 配合迭代器来工作，理论上应该大大提高了运行效率
        for (int i = k; i < N; i++) {
            // 使用迭代器提高效率
            ListIterator<Integer> iter = list.listIterator(list.size());
            boolean isDeleted = false;
            while (iter.hasPrevious() && iter.previous() < arr[i]) {
                // 发现比最后一个元素大时马上删除最后一个元素，此后不再删除元素
                if (!isDeleted) {
                    iter.remove();
                    isDeleted = true;
                }
            }
            if (iter.hasPrevious()) {
                iter.next();
            }
            if (isDeleted) {
                iter.add(arr[i]);
            }
        }

        return list.get(list.size() - 1);
    }

    // 采用一种简单的排序：冒泡排序，当然也可以用别的排序，
    // 但是这个问题的重点不是排序，所以选了一种简单的方法
    private static void bubbleSort(int[] arr, int begin, int end) {
        if (begin >= end) {
            return;
        }

        for (int i = begin; i < end - 1; i++) {
            boolean isSwap = false;
            for (int j = begin; j < end - begin -1; j++) {
                if (arr[j] < arr[j + 1]) {
                    isSwap = true;
                    int tmp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tmp;
                }
            }
            if (!isSwap) {
                return;
            }
        }
    }
}
