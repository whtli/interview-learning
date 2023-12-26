package org.example.ch01_java.ch08_method.p02_overload_with_caution;

import java.util.List;

/**
 * @author: whtli
 * @date: 2023/12/26
 * @description: 重写机制可以满足对方法调用行为的期望
 */
public class Overriding {
    public static void main(String[] args) {
        List<Wine> wineList = List.of(new Wine(), new SparklingWine(), new Champagne());
        for (Wine wine : wineList) {
            System.out.println(wine.name());
        }
    }
}

class Wine {
    String name() {
        return "wine";
    }
}

class SparklingWine extends Wine{
    @Override
    String name() {
        return "sparkling wine";
    }
}


class Champagne extends Wine{
    @Override
    String name() {
        return "champagne";
    }
}