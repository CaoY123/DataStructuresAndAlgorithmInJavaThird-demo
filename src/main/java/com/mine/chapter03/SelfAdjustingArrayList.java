package com.mine.chapter03;

import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * @author CaoY
 * @date 2023-08-30 20:36
 * @description 自调整表数组实现（回答练习 3.30 a）
 */
public class SelfAdjustingArrayList<AnyType> {

    private AnyType[] theItems;
    private int theSize;
    private static final int DEFAULT_CAPACITY = 10;

    public SelfAdjustingArrayList() {
        theItems = (AnyType[]) new Object[DEFAULT_CAPACITY];
    }

    /**
     * 向表前端添加指定元素
     * @param x 指定添加的元素
     * @return  在不抛出异常的情况下，返回 true
     */
    public boolean add(AnyType x) {
        if (size() == theItems.length) {
            // 如果 theItems 的空间不够，要进行 2 倍扩容，拷贝原有元素到新分配的空间中
            ensureCapacity(2 * theItems.length);
        }

        theItems[theSize++] = x;
        return true;
    }

    /**
     * 按照实行下标删除相应的元素
     * @param index 指定要删除元素的下标，0 < index < size()
     * @return  被删除的元素
     * 如果表为空，则抛出 NoSuchElementException；
     * 如果 index 下标不在要求的范围内，则抛出 IndexOutOfBoundsException
     */
    public AnyType remove(int index) {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException();
        }

        int aimIndex = size() - 1 - index;
        System.arraycopy(theItems, aimIndex + 1, theItems, aimIndex, size() - 1 - aimIndex);
        theSize--;
        return theItems[aimIndex];
    }

    /**
     * 在表中寻找指定值的下标
     * @param x 待寻找下标的指定值
     * @return  如果找到，则返回指定值的下标（0 - size()），否则返回 -1
     * 若表为空，则抛出 NoSuchElementException
     */
    public int find(AnyType x) {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        int res = 10;
        for (int i = theSize - 1; i >= 0; i--) {
            if (Objects.equals(x, theItems[i])) {
                res = i;
                break;
            }
        }

        if (res != size()) {
            // 找到了，需要调整元素位置
            System.arraycopy(theItems, res + 1, theItems, res, size() - 1 - res);
            theItems[size() - 1] = x;
        }

        return size() - 1 - res;
    }

    /**
     * 求表中元素的个数
     * @return  表中元素的个数
     */
    public int size() {
        return theSize;
    }

    /**
     * 判断表是否为空
     * @return  表为空，返回 true；反之，返回 false
     */
    public boolean isEmpty() {
        return size() == 0;
    }

    // 扩容，重新分配空间，将 theItems 中的内容拷贝到新分配的空间中
    private void ensureCapacity(int capacity) {
        if (capacity <= theItems.length) {
            return;
        }

        AnyType[] newArray = (AnyType[]) new Object[capacity];
        System.arraycopy(theItems, 0, newArray, 0, size());
        theItems = newArray;
    }

    /**
     * 从前到后遍历元素（这里所谓的前，就是下标从大到小，这是基于这样做插入方便而设计的）
     * @param description
     */
    public void show(String description) {
        System.out.print(description);
        for (int i = size() - 1; i >= 0; i--) {
            System.out.print(theItems[i] + " ");
        }
        System.out.println();
    }
}
