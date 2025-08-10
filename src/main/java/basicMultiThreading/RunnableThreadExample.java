package main.java.basicMultiThreading;

public class RunnableThreadExample {

    //Thread status - New, Runnable, Blocked, Waiting
    //Better way of thread creation is implementing runnable interface. If we use Thread class, only one class can be extended in the class.
    public static void main(String[] args) {
        Thread threadOne = new Thread(new ThreadOne());
        Thread threadTwo = new Thread(new ThreadTwo());
        Thread threadThree = new Thread(() -> {
            for (int i = 0; i < 20; i++) {
                System.out.println("ThreadThree: " + i);
            }
        });

        threadOne.start();
        threadTwo.start();
        threadThree.start();
    }
}

class ThreadOne implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            System.out.println("ThreadOne: " + i);
        }
    }
}

class ThreadTwo implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            System.out.println("ThreadTwo: " + i);
        }
    }
}
