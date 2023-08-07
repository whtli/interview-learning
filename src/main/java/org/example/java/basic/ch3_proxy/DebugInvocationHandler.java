package org.example.java.basic.ch3_proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author: whtli
 * @date: 2023/07/09
 * @description: 定义一个 JDK 动态代理类
 */
public class DebugInvocationHandler implements InvocationHandler {

    /**
     * 代理类中的真实对象
     */
    private final Object target;

    public DebugInvocationHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("---before method " + method.getName() + "---");
        Object result = method.invoke(target, args);
        System.out.println("---after method " + method.getName() + "---");
        return result;
    }
}
