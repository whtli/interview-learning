package org.example.ch01_java.ch01_basic.ch12_proxy;

import java.lang.reflect.Proxy;

/**
 * @author: whtli
 * @date: 2023/07/09
 * @description: 获取代理对象的工厂类
 */
public class JdkProxyFactory {
    /**
     * getProxy()：主要通过Proxy.newProxyInstance（）方法获取某个类的代理对象
     */
    public static Object getProxy(Object target) {
        return Proxy.newProxyInstance(
                // 目标类的类加载
                target.getClass().getClassLoader(),
                // 代理需要实现的接口，可指定多个
                target.getClass().getInterfaces(),
                // 代理对象对应的自定义 InvocationHandler
                new DebugInvocationHandler(target)
        );
    }
}
