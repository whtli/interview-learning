package org.example.basic;

/**
 * @author: whtli
 * @date: 2023/04/08
 * @description:
 */
public class Test {
    public static void main(String[] args) {
        Parent one = new Child("Li", 25);
        one.printInfo();
        Parent two = new Parent("Chen", 3);
        two.printInfo();
        two.printInfo(175);

    }
}
