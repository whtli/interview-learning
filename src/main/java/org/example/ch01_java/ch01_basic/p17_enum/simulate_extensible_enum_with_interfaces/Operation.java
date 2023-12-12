package org.example.ch01_java.ch01_basic.p17_enum.simulate_extensible_enum_with_interfaces;

import java.util.Arrays;
import java.util.Collection;

/**
 * @author: whtli
 * @date: 2023/12/12
 * @description: 用接口模拟可扩展的枚举
 */
public interface Operation {
    double apply(double x, double y);
}

class Test {
    public static void main(String[] args) {
        double x = Double.parseDouble("4.0");
        double y = Double.parseDouble("2.0");
        test1(ExtendOperation.class, x, y);
        test2(Arrays.asList(ExtendOperation.values()), x, y);
    }

    public static <T extends Enum<T> & Operation> void test1(Class<T> opEnumType, double x, double y) {
        for (Operation op : opEnumType.getEnumConstants()) {
            System.out.printf("%f %s % f = %f%n", x, op, y, op.apply(x, y));
        }
    }
    public static void test2(Collection<? extends Operation> opSet, double x, double y) {
        for (Operation op : opSet) {
            System.out.printf("%f %s % f = %f%n", x, op, y, op.apply(x, y));
        }
    }
}
