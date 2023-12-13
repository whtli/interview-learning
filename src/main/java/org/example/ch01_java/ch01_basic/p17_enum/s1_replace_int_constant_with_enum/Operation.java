package org.example.ch01_java.ch01_basic.p17_enum.s1_replace_int_constant_with_enum;

/**
 * @author: whtli
 * @date: 2023/11/08
 * @description: 需要将不同的行为与每个常量关联起来，以四则运算举例
 */
public class Operation {
    public static void main(String[] args) {
        double x = Double.parseDouble("2.0");
        double y = Double.parseDouble("4.0");
        // 三种实现方式的对比
        for (Operation1 op : Operation1.values()) {
            System.out.printf("%f %s %f = %f%n", x, op, y, op.apply(x, y));
        }
        System.out.println();
        for (Operation2 op : Operation2.values()) {
            System.out.printf("%f %s %f = %f%n", x, op, y, op.apply(x, y));
        }
        System.out.println();
        for (Operation3 op : Operation3.values()) {
            System.out.printf("%f %s %f = %f%n", x, op, y, op.apply(x, y));
        }
    }
}


/**
 * 方式1：弃用枚举的值来实现
 * 这种实现方式是脆弱的
 * 如果添加了新的枚举常量，却忘记给switch添加相应的条件，枚举仍然可以编译，但是当试图调用新的运算时，就会运行失败
 */
enum Operation1 {
    PLUS, MINUS, TIMES, DIVIDE;

    public double apply(double x, double y) {
        switch (this) {
            case PLUS:
                return x + y;
            case MINUS:
                return x - y;
            case TIMES:
                return x * y;
            case DIVIDE:
                return x / y;
        }
        // 如果没有throw语句则不能进行编译
        throw new AssertionError("Unknown operation: " + this);
    }
}

/**
 * 方式2：特定于常量的方法实现
 * 在枚举类中声明一个抽象的方法，并在特定于常量的类主体中用具体的方法覆盖每个常量的抽象方法
 */
enum Operation2 {
    PLUS {
        public double apply(double x, double y) {
            return x + y;
        }
    },
    MINUS {
        public double apply(double x, double y) {
            return x - y;
        }
    },
    TIMES {
        public double apply(double x, double y) {
            return x * y;
        }
    },
    DIVIDE {
        public double apply(double x, double y) {
            return x / y;
        }
    };

    public abstract double apply(double x, double y);
}

/**
 * ‘特定于常量的方法’与‘特定于常量的数据’结合起来
 * 重写toString方法以返回通常与该操作关联的符号
 */
enum Operation3 {
    PLUS("+") {
        public double apply(double x, double y) {
            return x + y;
        }
    },
    MINUS("-") {
        public double apply(double x, double y) {
            return x - y;
        }
    },
    TIMES("*") {
        public double apply(double x, double y) {
            return x * y;
        }
    },
    DIVIDE("/") {
        public double apply(double x, double y) {
            return x / y;
        }
    };

    private final String symbol;

    Operation3(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public String toString() {
        return symbol;
    }

    public abstract double apply(double x, double y);
}