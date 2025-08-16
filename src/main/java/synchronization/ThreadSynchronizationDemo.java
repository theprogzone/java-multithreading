package main.java.synchronization;

/**
 * Here expected value for counterOne variable is 20000.
 * But always after executing the programme, output value for counterOne is not 20000.
 * This is we called race condition.
 * This problem can be solved using the 'synchronized' key word.
 * 'synchronized' key word tells to each thread, use increment() method one at a time.
 */
public class ThreadSynchronizationDemo {

    public static int counterOne = 0;

    public static int counterTwo = 0;

    private static synchronized void increment() {
        counterTwo++;
    }

    public static void main(String[] args) {
        Thread threadOne = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                counterOne++;
                increment();
            }
        });

        Thread threadTwo = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                counterOne++;
                increment();
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

        System.out.println("Counter one value: " + counterOne);
        System.out.println("Counter two value: " + counterTwo);
    }
}
