package org.example.ch01_java.ch01_basic.p12_proxy;

/**
 * @author: whtli
 * @date: 2023/07/09
 * @description: 定义发送短信的接口
 */
public interface SmsService {
    String send(String message);
}