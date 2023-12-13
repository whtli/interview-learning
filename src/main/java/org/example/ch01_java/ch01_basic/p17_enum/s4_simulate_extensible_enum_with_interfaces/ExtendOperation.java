package org.example.ch01_java.ch01_basic.p17_enum.s4_simulate_extensible_enum_with_interfaces;

/**
 * @author: whtli
 * @date: 2023/12/12
 * @description: 用接口模拟可扩展的枚举
 */
public enum ExtendOperation implements Operation {
    EXP("^") {
        public double apply(double x, double y) {
            return Math.pow(x, y);
        }
    },
    REMAINDER("%") {
        public double apply(double x, double y) {
            return x % y;
        }
    };
    private final String symbol;

    ExtendOperation(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public String toString() {
        return symbol;
    }
}