package org.example.ch01_java.ch04_concurrent.p07_atomic;

import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @author: whtli
 * @date: 2023/10/08
 * @description: AtomicStampedReference功能测试
 */
public class AtomicStampedReferenceTest {
    public static void main(String[] args) {
        // 实例化、取当前值和stamp值
        final Integer initReference = 0, initStamp = 0;
        final AtomicStampedReference<Integer> asr = new AtomicStampedReference<>(initReference, initStamp);
        System.out.println("currentValue=" + asr.getReference() + ", currentStamp=" + asr.getStamp());

        // compare and set
        final Integer newReference = 666, newStamp = 999;
        final boolean casResult = asr.compareAndSet(initReference, newReference, initStamp, newStamp);
        System.out.println("currentValue=" + asr.getReference() + ", currentStamp=" + asr.getStamp() + ", casResult=" + casResult);

        // 获取当前的值和当前的stamp值
        int[] arr = new int[1];
        final Integer currentValue = asr.get(arr);
        final int currentStamp = arr[0];
        System.out.println("currentValue=" + currentValue + ", currentStamp=" + currentStamp);

        // 单独设置stamp值
        final boolean attemptStampResult = asr.attemptStamp(newReference, 88);
        System.out.println("currentValue=" + asr.getReference() + ", currentStamp=" + asr.getStamp() + ", attemptStampResult=" + attemptStampResult);

        // 重新设置当前值和stamp值
        asr.set(initReference, initStamp);
        System.out.println("currentValue=" + asr.getReference() + ", currentStamp=" + asr.getStamp());
    }
}
