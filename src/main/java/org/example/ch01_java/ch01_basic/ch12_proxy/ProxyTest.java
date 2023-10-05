package org.example.ch01_java.ch01_basic.ch12_proxy;

/**
 * @author: whtli
 * @date: 2023/07/09
 * @description:
 */
public class ProxyTest {
    public static void main(String[] args) {
        SmsService smsService = (SmsService) JdkProxyFactory.getProxy(new SmsServiceImpl());
        smsService.send("java");
    }
}
