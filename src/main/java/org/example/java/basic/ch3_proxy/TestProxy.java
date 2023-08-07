package org.example.java.basic.ch3_proxy;

/**
 * @author: whtli
 * @date: 2023/07/09
 * @description:
 */
public class TestProxy {
    public static void main(String[] args) {
        SmsService smsService = (SmsService) JdkProxyFactory.getProxy(new SmsServiceImpl());
        smsService.send("java");
    }
}
