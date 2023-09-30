package org.example.java.basic.ch02_package_cache;

/**
 * @author: whtli
 * @date: 2023/09/30
 * @description: 包装类型的缓存机制
 * Java 基本数据类型的包装类型的大部分都用到了缓存机制来提升性能。
 * 如果超出对应范围仍然会去创建新的对象，缓存的范围区间的大小只是在性能和资源之间的权衡。
 * Byte,Short,Integer,Long 包装类默认创建了数值 [-128，127] 的相应类型的缓存数据
 * Character 创建了数值在 [0,127] 范围的缓存数据
 * Boolean 直接返回 True or False。
 * Float,Double 没有实现缓存机制。
 */
public class PackageCacheTest {
    public static void main(String[] args) {
        Integer i1 = 33;
        Integer i2 = 33;
        // 输出 true
        System.out.println(i1 == i2);

        Float i3 = 333f;
        Float i4 = 333f;
        // 输出 false
        System.out.println(i3 == i4);

        Double i5 = 1.2;
        Double i6 = 1.2;
        // 输出 false
        System.out.println(i5 == i6);

        // 输出 false
        Integer i7 = 40;
        Integer i8 = new Integer(40);
        System.out.println(i7 == i8);


    }
}
