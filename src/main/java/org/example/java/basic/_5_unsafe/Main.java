package org.example.java.basic._5_unsafe;

import sun.misc.Unsafe;

/**
 * @author: whtli
 * @date: 2023/07/09
 * @description: 内存屏障
 */

public class Main {
    public static void main(String[] args) {
        ChangeThread changeThread = new ChangeThread();
        new Thread(changeThread).start();
        while (true) {
            boolean flag = changeThread.isFlag();
            // 加入读内存屏障
            // 此代码跑不通
            // 只有启动类加载器加载的类才能够调用 Unsafe 类中的方法，来防止这些方法在不可信的代码中被调用
            Unsafe.getUnsafe().loadFence();
            if (flag) {
                System.out.println("detected flag changed");
                break;
            }
        }
        System.out.println("main thread is over");
    }
}

class ChangeThread implements Runnable {
    boolean flag = false;

    public boolean isFlag() {
        return flag;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("subThread change flag to:" + flag);
        flag = true;
    }
}


