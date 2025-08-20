package main.java.waitAndNotify;

/**
 * First method locking the object.
 * Second method notify the first thread and then later first thread release the object.
 */

public class WaitAndNotifyDemo {

    private static final Object LOCK = new Object();

    public static void main(String[] args) {
        Thread threadOne = new Thread(() -> {
            try {
                one();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        Thread threadTwo = new Thread(() -> {
                two();
        });

        threadOne.start();
        threadTwo.start();
    }

    private static void one() throws InterruptedException {
        synchronized (LOCK) {
            System.out.println("Hello from method one.");
            LOCK.wait();
            System.out.println("Back again in the method one.");
        }
    }

    private static void two() {
        synchronized (LOCK) {
            System.out.println("Hello from method two.");
            LOCK.notify();
            System.out.println("Back again in the method two.");
        }
    }
}
