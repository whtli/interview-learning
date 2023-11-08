package org.example.ch01_java.ch01_basic.p17_enum.use_instance_not_order;

/**
 * @author: whtli
 * @date: 2023/11/08
 * @description: 用实例阈代替序数
 */

public class Ensemble {
    public static void main(String[] args) {
        // 两种实现方式的对比
        for (Ensemble1 item : Ensemble1.values()) {
            System.out.printf("%s %d%n", item, item.numberOfMusicians());
        }
        System.out.println();
        for (Ensemble2 item : Ensemble2.values()) {
            System.out.printf("%s %d%n", item, item.getNumberOfMusicians());
        }
    }
}

enum Ensemble1 {
    SOLO,
    DUET,
    TRIO,
    QUARTET,
    QUINTET,
    SEXTET,
    SEPTET,
    OCTET,
    DOUBLE_QUARTET,
    NONET,
    DECTET,
    TRIPLE_QUARTET;

    public final int numberOfMusicians() {
        return ordinal() + 1;
    }
}

enum Ensemble2 {
    SOLO(1),
    DUET(2),
    TRIO(3),
    QUARTET(4),
    QUINTET(5),
    SEXTET(6),
    SEPTET(7),
    OCTET(8),
    DOUBLE_QUARTET(8),
    NONET(9),
    DECTET(10),
    TRIPLE_QUARTET(12);
    private final int numberOfMusicians;

    Ensemble2(int size) {
        this.numberOfMusicians = size;
    }

    public int getNumberOfMusicians() {
        return numberOfMusicians;
    }
}
