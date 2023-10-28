package org.example.ch01_java.ch01_basic.p12_proxy;

/**
 * @author: whtli
 * @date: 2023/07/09
 * @description: 定义发送短信的接口
 */
public interface SmsService {
    /**
     * 发送信息的方法
     * @param message 信息参数
     * @return 字符串
     */
    String send(String message);
}
