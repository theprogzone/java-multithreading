package main.java.basicMultiThreading;

public class ThreadJoinExample {

    public static void main(String[] args) throws InterruptedException {
        Thread threadOne = new Thread(() -> {
            for (int i = 0; i < 20; i++) {
                System.out.println("ThreadOne " + i);
            }
        });

        Thread threadTwo = new Thread(() -> {
            for (int i = 0; i < 20; i++) {
                System.out.println("ThreadTwo " + i);
            }
        });

        System.out.println("Thread execution starting!");
        threadOne.start();
        threadTwo.start();
        threadOne.join(); // Main thread is waiting until finishes threadOne
        threadTwo.join(); // Main thread is waiting until finishes threadTwo
        System.out.println("Thread execution end!");
    }
}
