package exercise2;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class PingPong {

    private volatile boolean running = true;
    private volatile boolean isPing = true;
    private final Queue<String> results = new ConcurrentLinkedQueue<>();

    public void start() throws InterruptedException {
        Thread ping = new Thread(() -> {
            while (running) {
                if (isPing) {
                    System.out.println("ping");
                    results.add("ping");
                    isPing = false;
                }
            }
        });

        Thread pong = new Thread(() -> {
            while (running) {
                if (!isPing) {
                    System.out.println("pong");
                    results.add("pong");
                    isPing = true;
                }
            }
        });

        ping.start();
        pong.start();

        Thread.sleep(5000);
        running = false;
        ping.join();
        pong.join();
    }

    public Queue<String> getResults() { return results; }

    public static void main(String[] args) throws InterruptedException {
        new PingPong().start();
    }
}
