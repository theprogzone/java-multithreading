package main.java.basicMultiThreading;

/**
 * Thread priority is number between 1 - 10.
 * Lowest priority is 1 and max priority is 10.
 * If we not set the priority to a thread, by default priority will be 5.
 * Main thread always take the first priority to execute.
 */
public class ThreadPriorityExample {

    public static void main(String[] args) {
        System.out.println("Executing thread "+Thread.currentThread().getName());
        Thread threadOne = new Thread(() -> {
            System.out.println("Executing thread "+Thread.currentThread().getName());
        });
        Thread threadTwo = new Thread(() -> {
            System.out.println("Executing thread "+Thread.currentThread().getName());
        });
        threadOne.setPriority(Thread.MAX_PRIORITY);
        threadTwo.setPriority(Thread.MIN_PRIORITY);
        threadOne.start();
        threadTwo.start();
    }
}
