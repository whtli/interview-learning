package org.example.ch01_java.ch04_concurrent.atomic;

import java.util.concurrent.atomic.AtomicMarkableReference;

/**
 * @author: whtli
 * @date: 2023/10/08
 * @description: AtomicMarkableReference功能测试
 */
public class AtomicMarkableReferenceTest {
    public static void main(String[] args) {
        // 实例化、取当前值和mark值
        final Boolean initReference = null, initMark = false;
        final AtomicMarkableReference<Boolean> amr = new AtomicMarkableReference<>(initReference, initMark);
        System.out.println("currentValue=" + amr.getReference() + ", currentMark=" + amr.isMarked());

        // compare and set
        final Boolean newReference = true, newMark = true;
        final boolean casResult = amr.compareAndSet(initReference, newReference, initMark, newMark);
        System.out.println("currentValue=" + amr.getReference() + ", currentMark=" + amr.isMarked() + ", casResult=" + casResult);

        // 获取当前的值和当前的mark值
        boolean[] arr = new boolean[1];
        final Boolean currentValue = amr.get(arr);
        final boolean currentMark = arr[0];
        System.out.println("currentValue=" + currentValue + ", currentMark=" + currentMark);

        // 单独设置mark值
        final boolean attemptMarkResult = amr.attemptMark(newReference, false);
        System.out.println("currentValue=" + amr.getReference() + ", currentMark=" + amr.isMarked() + ", attemptMarkResult=" + attemptMarkResult);

        // 重新设置当前值和mark值
        amr.set(initReference, initMark);
        System.out.println("currentValue=" + amr.getReference() + ", currentMark=" + amr.isMarked());
    }
}

