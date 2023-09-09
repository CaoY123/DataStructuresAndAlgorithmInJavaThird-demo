package com.mine.chapter04.practice;

import com.mine.chapter04.MyTreeMap;
import com.mine.chapter04.MyTreeSet;

import java.util.List;

/**
 * @author CaoY
 * @date 2023-09-09 15:46
 * @description 练习 4.12
 * 通过存储类型 TreeSet<Map.Entry<KeyType, ValueType> > 的一个数据成员编写实现 TreeMap 类的程序
 */
public class Solution04_12 {

    public static void main(String[] args) {
        MyTreeMap<Integer, String> map = new MyTreeMap<>();
        map.put(1, "张三");
        map.put(2, "李四");
        map.put(3, "王五");
        map.put(4, "赵六");
        map.put(5, "候七");
        for (MyTreeMap.Entry<Integer, String> entry : map.entrySet()) {
            System.out.println(entry.key + " -> " + entry.value);
        }
        System.out.println("***************************************");

        MyTreeSet<Integer> keySet = map.keySet();
        keySet.allOrderShow("打印键：");
        List<String> values = map.values();
        System.out.println("打印值：" + values);
        System.out.println("***************************************");

        map.remove(1);
        map.remove(3);
        map.remove(5);
        System.out.println("删除键 1、3、5 后：");
        for (MyTreeMap.Entry<Integer, String> entry : map.entrySet()) {
            System.out.println(entry.key + " -> " + entry.value);
        }
        System.out.println("***************************************");

        System.out.println("包含值\'张三\'吗：" + (map.containsValue("张三") ? "包含" : "不包含"));
        System.out.println("包含值\'李四\'吗：" + (map.containsValue("李四") ? "包含" : "不包含"));
        System.out.println("***************************************");

        System.out.println("包含键 1 吗：" + (map.containsKey(1) ? "包含" : "不包含"));
        System.out.println("包含键 2 吗：" + (map.containsKey(2) ? "包含" : "不包含"));

        System.out.println("该 map 的元素个数：" + map.size());
        map.clear();
        System.out.println("clear 后，该 map 的元素个数：" + map.size());
        System.out.println("***************************************");

        map.put(12, "地球");
        for (MyTreeMap.Entry<Integer, String> entry : map.entrySet()) {
            System.out.println(entry.key + " -> " + entry.value);
        }
        System.out.println("***************************************");
    }
}
