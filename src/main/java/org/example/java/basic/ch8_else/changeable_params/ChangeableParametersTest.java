package org.example.java.basic.ch8_else.changeable_params;

/**
 * @author: whtli
 * @date: 2023/09/13
 * @description: 可变长参数
 */
public class ChangeableParametersTest {
    public static void main(String[] args) {
        ChangeableParameters test = new ChangeableParameters();
        test.changeableParamMethod("hello", "world");
        test.changeableParamMethod("a", "b", "c", "d");
    }
}

/**
 * 遇到方法重载的情况，会优先匹配固定参数的方法，因为固定参数的方法匹配度更高
 */
class ChangeableParameters {
    public void changeableParamMethod(String a, String b) {
        System.out.println(a + b);
    }

    public void changeableParamMethod(String... args) {
        for (String c : args) {
            System.out.println(c);
        }
    }
}
