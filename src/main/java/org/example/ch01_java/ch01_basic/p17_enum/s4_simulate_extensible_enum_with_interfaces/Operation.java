package org.example.ch01_java.ch01_basic.p17_enum.s4_simulate_extensible_enum_with_interfaces;

import java.util.Arrays;
import java.util.Collection;

/**
 * @author: whtli
 * @date: 2023/12/12
 * @description: 用接口模拟可扩展的枚举
 * 虽然无法编写可扩展的枚举类型，却可以通过编写接口以及实现该接口的基础枚举类型来对它进行模拟
 * 这样允许客户端编写自己的枚举（或其他类型）来实现接口
 */
public interface Operation {
    /**
     * 对两个操作数进行指定运算
     * @param x 操作数1
     * @param y 操作数2
     * @return 运算结果
     */
    double apply(double x, double y);
}

class Test {
    public static void main(String[] args) {
        double x = Double.parseDouble("4.0");
        double y = Double.parseDouble("2.0");
        // 测试1
        test1(ExtendOperation.class, x, y);
        // 测试2
        test2(Arrays.asList(ExtendOperation.values()), x, y);
    }

    /**
     * ExtendOperation.class代表有限制的类型令牌（bounded type token）
     * <T extends Enum<T> & Operation> Class<T>确保了Class对象既表示枚举又表示Operation的子类
     */
    public static <T extends Enum<T> & Operation> void test1(Class<T> opEnumType, double x, double y) {
        for (Operation op : opEnumType.getEnumConstants()) {
            System.out.printf("%f %s % f = %f%n", x, op, y, op.apply(x, y));
        }
    }

    /**
     * 传入的Collection<? extends Operation> 代表有限制的通配符类型（bounded wildcard type）
     * 这种实现代码相对简单，方法也比较灵活，允许调用者将多个实现类型的操作合并到一起
     * 但是也放弃了在指定操作上使用EnumSet和EnumMap的功能
     */
    public static void test2(Collection<? extends Operation> opSet, double x, double y) {
        for (Operation op : opSet) {
            System.out.printf("%f %s % f = %f%n", x, op, y, op.apply(x, y));
        }
    }
}
