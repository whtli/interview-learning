package org.example.ch01_java.ch01_basic.p04_override_overload;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @author: whtli
 * @date: 2023/09/11
 * @description: 重载和重写
 * 重载，发生阶段：编译期
 */

public class OverloadTest {
    /**
     * 方法名必须相同
     * 参数列表必须不同
     */
    public void method1() {

    }

    public void method1(int val) {

    }

    /**
     * 返回值可以不同
     */
    public void method2() {

    }

    public String method2(int val, String str) {
        return str + val;
    }

    /**
     * 异常可以不同
     */
    public void method3() throws IOException {

    }

    public String method3(String str) throws FileNotFoundException {
        return str;
    }

    /**
     * 访问修饰符可以不同
     */
    public void method4() {
    }

    private void method4(String str) {
    }

    protected void method4(int val) {
    }
}
