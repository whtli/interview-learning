package org.example.java.basic._6_sugar;

/**
 * @author: whtli
 * @date: 2023/07/09
 * @description: 可变长参数
 */
public class VariableArguments {
    public static void main(String[] args) {
        print("Holis", "公众号:Hollis", "博客：www.hollischuang.com", "QQ：907607222");
    }

    public static void print(String... strs) {
        for (String str : strs) {
            System.out.println(str);
        }
    }
}
