package com.mine.chapter04.practice;

/**
 * @author CaoY
 * @date 2023-09-10 21:09
 * @description 练习 4.29
 * a. 证明如果按顺序访问伸展树中的所有结点，则所得到的树由一连串的左儿子组成。
 * b. 证明如果按顺序访问伸展树中的所有结点，则总的访问时间是 O(N)，与初始树无关。
 *
 * 回答：
 *  a. 这个很好理解，这个按顺序访问就是从小到大访问，每次将访问的元素放到根部，
 *  然后将之前的挂在其左子树上，所以就可以明白 a 是正确的。
 *  b. 这个证明不太会，直观感觉与深度有关还有每次访问进行的伸展操作会使得查找下一个相邻较大的元素
 *  更容易，这样感觉貌似这个是正确的，但是不好理解，这里不证明了，当个结论简单看一下就行。
 */
public class Solution04_29 {

    public static void main(String[] args) {
        System.out.println("第一个简单，第二个不好理解。");
    }
}
