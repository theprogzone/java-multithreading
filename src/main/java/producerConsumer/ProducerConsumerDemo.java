package main.java.producerConsumer;

import java.util.ArrayList;
import java.util.List;

public class ProducerConsumerDemo {

    public static void main(String[] args) {
        Worker worker = new Worker(10, 0);

        Thread producer = new Thread(() -> {
            try {
                worker.produce();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        Thread consumer = new Thread(() -> {
            try {
                worker.consume();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        producer.start();
        consumer.start();
    }
}

 class Worker {
    private Integer sequence = 0;
    private Integer top;
    private Integer bottom;
    private List<Integer> containers;
    private final Object lock = new Object();

    public Worker(Integer top, Integer bottom) {
        this.top = top;
        this.bottom = bottom;
        this.containers = new ArrayList<>();
    }

    public void produce() throws InterruptedException {

        synchronized (lock) {
            while (true) {
                if (containers.size() == top) {
                    System.out.println("Container is full now. Waiting to be unloaded.");
                    lock.wait();
                } else {
                    System.out.println("Loading items to container: "+ ++this.sequence);
                    containers.add(this.sequence);
                    lock.notify();
                }
                Thread.sleep(500);
            }
        }
    }

    public void consume() throws InterruptedException {
        synchronized (lock) {
            while (true) {
                if (containers.size() == bottom) {
                    System.out.println("Container is empty now. Waiting to be loaded.");
                    lock.wait();
                } else {
                    System.out.println("Unloading items from container: "+ containers.removeFirst());
                    lock.notify();
                }
                Thread.sleep(500);
            }
        }
    }
}