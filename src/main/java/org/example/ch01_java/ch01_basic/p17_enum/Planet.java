package org.example.ch01_java.ch01_basic.p17_enum;

/**
 * @author: whtli
 * @date: 2023/11/08
 * @description: enum的使用demo，每个Planet常量关联了不同的数据
 */
public enum Planet {
    MERCURY(3.302e+23, 2.439e6),
    VENUS(4.869e+24, 6.052e6),
    EARTH(5.975e+24, 6.378e6),
    MARS(6.419e+23, 3.393e6),
    JUPITER(1.899e+27, 7.149e7),
    SATURN(5.685e+26, 6.027e7),
    URANUS(8.683e+25, 2.556e7),
    NEPTUNE(1.024e+26, 2.477e7);

    // 行星质量
    private final double mass;
    // 行星半径
    private final double radius;
    // 行星的表面重力加速度
    private final double surfaceGravity;
    // 万有引力常数
    private static final double G = 6.67300e-11;

    // 构造器
    Planet(double mass, double radius) {
        this.mass = mass;
        this.radius = radius;
        // G*m/r^2
        this.surfaceGravity = G * mass / (radius * radius);
    }

    public double mass() {
        return mass;
    }

    public double radius() {
        return radius;
    }

    public double surfaceGravity() {
        return surfaceGravity;
    }

    public double surfaceWeight(double mass) {
        // F=ma
        return mass * surfaceGravity;
    }
}

/**
 * 根据某个物体在地球上的重量（以任何单位），打印出该物体在所有8颗行星上的重量（以相同单位）
 */
class WeightTable {
    public static void main(String[] args) {
        double earthWeight = Double.parseDouble("185.00");
        double mass = earthWeight / Planet.EARTH.surfaceGravity();
        for (Planet p : Planet.values()) {
            System.out.printf("Weight on %s is %f%n", p, p.surfaceWeight(mass));
        }
    }
}