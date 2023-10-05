package org.example.ch01_java.ch01_basic.ch09_finalizer_cleaner;

/*

import java.lang.ref.Cleaner;

*/
/**
 * @author: whtli
 * @date: 2023/09/28
 * @description: 避免使用终结方法和清楚方法
 * @source: Effective Java
 * An autocloseable class using a cleaner as a safety net
 * 需要配置jdk9及以上才可以允许，8没有相关依赖
 *//*

public class Room implements AutoCloseable {
    private static final Cleaner cleaner = Cleaner.create();

    // Resource that requires cleaning. Must not refer to Room
    private static class State implements Runnable {
        // Number of junk piles in this room
        int numJunkPiles;

        State(int numJunkPiles) {
            this.numJunkPiles = numJunkPiles;
        }

        @Override
        public void run() {
            System.out.println("Cleaning room");
            numJunkPiles = 0;
        }
    }

    // The state of this room, shared with our cleanable
    private final State state;

    // Our cleanable. Cleans the room when it's eligible for gc
    private final Cleaner.Cleanable cleanable;

    public Room(int numJunkPoles) {
        state = new State(numJunkPoles);
        cleanable = cleaner.register(this, state);
    }

    @Override
    public void close() throws Exception {
        cleanable.clean();
    }
}

class Adult {
    public static void main(String[] args) throws Exception {
        try (Room myRoom = new Room(7)) {
            System.out.println("goodbye");
        }

        System.out.println();
        new Room(99);
        System.out.println("Peace out");
    }
}*/
