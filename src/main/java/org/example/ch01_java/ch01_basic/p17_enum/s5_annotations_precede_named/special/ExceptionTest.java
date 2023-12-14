package org.example.ch01_java.ch01_basic.p17_enum.s5_annotations_precede_named.special;

import java.lang.annotation.*;

/**
 * @author: whtli
 * @date: 2023/12/14
 * @description: 表明带注释的方法是必须抛出指定异常才能成功的测试方法
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Repeatable(ExceptionTestContainer.class)
public @interface ExceptionTest {
    // 版本1
    // Class<? extends Throwable> value();

    // 版本2：使用了新版的数组参数之后，之前所有的ExceptionTest注解仍然有效，并产生单元素的数组
    // Class<? extends Exception>[] value();

    // 版本3
    Class<? extends Throwable> value();
}

