package com.mine.chapter04;

import java.util.*;

/**
 * @author CaoY
 * @date 2023-09-09 15:51
 * @description 自定义的 TreeMap 实现类，回答 练习 4.12
 *
 * 这是一个设计的很不好的类，主要是连着一个题要我写整个树的类写的我有点麻，烦躁了。
 * 此外笔者本人还是没有找出一个合适的策略，参考 JDK8 的 TreeMap 的设计也感觉很
 * 复杂，以我现在的功力很难吃透，所以先暂时攒了这么一个垃圾出来，待后续吃透专业的
 * 设计后，期望再做改进，或者由可能的读者提供更好的实现，若如此，则不胜感激！
 * todo 后面对于这个 TreeMap 的实现要吃透 JDK8 的代码后再做考虑
 */
public class MyTreeMap<KeyType extends Comparable<? super KeyType>, ValueType> {

    private MyTreeSet<Entry<KeyType, ValueType>> totalSet;
    private MyTreeSet<KeyType> keySet;
    private List<ValueType> values;

    public MyTreeMap() {
        totalSet = new MyTreeSet<>();
        keySet = new MyTreeSet<>();
    }

    public int size() {
        return totalSet.size();
    }

    public boolean isEmpty() {
        return totalSet.isEmpty();
    }

    public boolean containsKey(KeyType key) {
        if (key == null) {
            throw new NullPointerException();
        }

        return keySet.contains(key);
    }
    
    public boolean containsValue(ValueType value) {
        if (values == null) {
            values = values();
        }
        return values.contains(value);
    }

    public ValueType get(KeyType key) {
        if (key == null) {
            return null;
        }

        Entry<KeyType, ValueType> entry = getEntry(key);
        return entry != null ? entry.value : null;
    }

    public ValueType put(KeyType key, ValueType value) {
        totalSet.insert(new Entry<>(key, value));
        keySet.insert(key);
        values = null;
        return value;
    }

    public ValueType remove(KeyType key) {
        Entry<KeyType, ValueType> entry = getEntry(key);
        totalSet.remove(new Entry<>(key, entry.value));
        keySet.remove(key);
        values = null;
        return entry.value;
    }

    public void clear() {
        totalSet = new MyTreeSet<>();
        keySet = new MyTreeSet<>();
        values = null;
    }

    // 实际上不安全，需要拷贝，但是懒得实现了
    public MyTreeSet<KeyType> keySet() {
        return keySet;
    }


    public List<ValueType> values() {
        Iterator<KeyType> iter = keySet.iterator();
        values = new ArrayList<>();
        while (iter.hasNext()) {
            Entry<KeyType, ValueType> entry = getEntry(iter.next());
            values.add(entry.value);
        }

        return values;
    }

    // 也是比较危险的，没有禁止用户对它的修改操作
    public MyTreeSet<Entry<KeyType, ValueType>> entrySet() {
        return totalSet;
    }

    private Entry<KeyType, ValueType> getEntry(KeyType key) {
        MyTreeSet<Entry<KeyType, ValueType>>.BinaryNode<Entry<KeyType, ValueType>> node =
                totalSet.getNode(new Entry<>(key));
        return new Entry<>(node.element.key, node.element.value);
    }

    public static class Entry<KeyType extends Comparable<? super KeyType>, ValueType>
            implements Comparable<Entry<KeyType, ValueType> >{
        public KeyType key;
        public ValueType value;

        public Entry(KeyType key) {
            this.key = key;
            this.value = null;
        }

        public Entry(KeyType key, ValueType value) {
            this.key = key;
            this.value = value;
        }

        
        public int compareTo(Entry<KeyType, ValueType> o) {
            if (o == null) {
                throw new NullPointerException();
            }

            return this.key.compareTo(o.key);
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == null) {
                return false;
            }
            Entry<KeyType, ValueType> obj1 = (Entry<KeyType, ValueType>) obj;
            return key.equals(obj1);
        }
    }
}
