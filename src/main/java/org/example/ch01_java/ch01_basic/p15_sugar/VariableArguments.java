package org.example.ch01_java.ch01_basic.p15_sugar;

/**
 * @author: whtli
 * @date: 2023/07/09
 * @description: 可变长参数
 */
public class VariableArguments {
    public static void main(String[] args) {
        print("whtli", "公众号:whtli", "博客：http://hexo.whtli.cn/", "QQ：123456789");
        print("公众号:whtli", "博客：http://hexo.whtli.cn/");
    }

    public static void print(String... strs) {
        for (String str : strs) {
            System.out.println(str);
        }
        System.out.println();
    }
}
