package org.example.ch01_java.ch01_basic.p12_proxy;

/**
 * @author: whtli
 * @date: 2023/07/09
 * @description: 实现发送短信的接口
 */
public class SmsServiceImpl implements SmsService {

    @Override
    public String send(String message) {
        System.out.println("send message:" + message);
        return message;
    }
}
