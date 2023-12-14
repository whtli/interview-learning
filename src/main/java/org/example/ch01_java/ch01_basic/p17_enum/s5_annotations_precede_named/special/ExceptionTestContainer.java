package org.example.ch01_java.ch01_basic.p17_enum.s5_annotations_precede_named.special;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/**
 * @author: whtli
 * @date: 2023/12/14
 * @description: 包含注解类型
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface ExceptionTestContainer {
    ExceptionTest[] value();
}
