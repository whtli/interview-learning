package org.example.other;

import java.util.Arrays;

/**
 * @author: whtli
 * @date: 2023/10/03
 * @description: System.arraycopy()与Arrays.copyOf()的使用区别
 */
public class ArrayCopyTest {
    public static void main(String[] args) {
        int[] sourceArray1 = {11, 12, 13, 14, 15};
        int[] targetArray1 = new int[3];
        // arraycopy可以指定复制的起始位置，功能更丰富
        System.arraycopy(sourceArray1, 1, targetArray1, 1, 2);
        // 结果：targetArray1 = {0, 22, 33}
        System.out.println(Arrays.toString(targetArray1));

        int[] sourceArray2 = {21, 22, 23, 24, 25};
        // copyOf只能从源数组的头部开始复制
        int[] targetArray2 = Arrays.copyOf(sourceArray2, 4);
        // 结果：targetArray2 = {21, 22, 23, 24}
        System.out.println(Arrays.toString(targetArray2));
    }
}
