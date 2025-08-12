package main.java.basicMultiThreading;

/**
 * Daemon threads are running in the background
 * Once the all other threads stop execution JVM stops the execution of daemon thread
 */
public class DaemonThreadExample {
    public static void main(String[] args) {
        Thread threadOne = new Thread(new DaemonHelper());
        Thread threadTwo = new Thread(new UserThreadHelper());

        threadOne.setDaemon(true);
        threadOne.start();
        threadTwo.start();
    }
}

class DaemonHelper implements Runnable {

    @Override
    public void run() {
        int count = 0;
        while (count < 500) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Daemon thread running");
            count++;
        }
    }
}

class UserThreadHelper implements Runnable {

    @Override
    public void run() {
        System.out.println("User thread starting");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("User thread finished");
    }
}
