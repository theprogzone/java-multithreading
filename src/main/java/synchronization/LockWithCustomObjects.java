package main.java.synchronization;

/**
 * Issues with the synchronized keyword with methods
 * Performance overhead – Every synchronized method acquires and releases a monitor lock, which introduces additional overhead and can reduce application performance, especially under high contention.
 * Overly broad locking – When the synchronized keyword is applied on method level, In many cases, this forces synchronization of an entire method, even if only a small section truly requires mutual exclusion. This can lead to unnecessary blocking.
 * Inheritance limitations – If a subclass overrides a method and makes it synchronized, while the superclass method is not, it may lead to unexpected behavior or concurrency issues. Consistency in synchronization across inheritance hierarchies is required, otherwise subtle bugs can occur.
 * Here, the lockOne and lockTwo objects are used as dedicated monitors for their respective synchronized blocks. Each block acquires the monitor lock of its associated object, ensuring thread safety without unnecessary contention between unrelated operations.
 */

public class LockWithCustomObjects {

    public static int counterOne = 0;
    public static int counterTwo = 0;

    private static final Object lockOne = new Object();
    private static final Object lockTwo = new Object();

    public static void main(String[] args) {
        Thread threadOne = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                incrementOne();
            }
        });

        Thread threadTwo = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                incrementTwo();
            }
        });

        threadOne.start();
        threadTwo.start();

        try {
            threadOne.join();
            threadTwo.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Counter one value: " + counterOne + " Counter two value: " + counterTwo);
    }

    private static void incrementOne() {
        synchronized (lockOne) {
            counterOne++;
        }
    }

    private static void incrementTwo() {
        synchronized (lockTwo) {
            counterTwo++;
        }
    }
}
