package com.mine.chapter03.practice;

/**
 * @author CaoY
 * @date 2023-08-27 15:55
 * @description 练习 3.19 实现一个不用头结点和尾结点的 LinkedList 类，并使用它与
 * 使用头结点和尾结点的 MyLinkedList 类。
 *
 * 回答：就不实现这个不使用头结点和尾结点的 LinkedList 类了，因为比较繁琐，会很耗费时间，因此不实现了。
 * 因为它没有使用头结点和尾结点，所以在涉及尾结点的操作后，必须使用迭代来找到尾结点，这回导致它们的时间复杂度
 * 扩大至 O(N)，还有就是因为没有头结点和尾结点，对于插入和删除的开始和结尾处的操作需要作特殊情况处理，这实际
 * 上增加了编码的难度。因此，还是需要使用头结点和尾结点，可以使得统一处理不同的插入和删除情况（头元素、中间元素、
 * 尾元素），降低编码难度，不容易出错。
 */
public class Solution03_19 {

    public static void main(String[] args) {
        System.out.println("Hello, List API!");
    }
}
