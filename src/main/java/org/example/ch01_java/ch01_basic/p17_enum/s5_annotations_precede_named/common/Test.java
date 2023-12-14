package org.example.ch01_java.ch01_basic.p17_enum.s5_annotations_precede_named.common;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author: whtli
 * @date: 2023/12/14
 * @description: 表明带注释的方法是测试方法，仅在无参静态方法上使用
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Test {
}
