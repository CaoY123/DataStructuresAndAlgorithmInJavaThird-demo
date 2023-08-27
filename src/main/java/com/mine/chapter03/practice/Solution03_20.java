package com.mine.chapter03.practice;

/**
 * @author CaoY
 * @date 2023-08-27 16:07
 * @description 练习 3.20 实现链表（MyLinkedList）的懒惰删除
 *
 * 注：这里不作实现了，因为会使 MyLinkerList 的 API 不清晰，一会儿这个，一会儿那个，看得不清晰。
 * 如果一定要实现，那也简单，需要现在 Node 里加一个 boolean 类型的 isDeleted（开始为false） 字段，
 * 然后在 MyLinkedList 中添加一个 lazyDelete()，每次调用的时候找到这个要删除的元素，将 isDeleted
 * 设置为 true。接着记录已经删除的元素数量 +1，当这个数目达到要表中元素数目的一半时，就遍历整个表删除。
 *
 * 对比原来立即删除的策略，我觉得其优点就是：可以后面在合适的时候恢复数据。至于答案所提的编码更简单，
 * 貌似编码也不简单，这一个改变也需要一些检验的代码来支撑，还有就是增加了空间（Node 里增加了一个字段），
 * 所以我觉得其优势也不明显。
 */
public class Solution03_20 {

    public static void main(String[] args) {

    }
}
