import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

class Logger {
    private BlockingQueue<String> logQueue = new LinkedBlockingQueue<>();

    public void log(String message) {
        try {
            logQueue.put(message);  // Block if the queue is full
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void startLogging() {
        new Thread(() -> {
            try {
                while (true) {
                    String logMessage = logQueue.take();  // Block if the queue is empty
                    System.out.println("Writing to log: " + logMessage);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }).start();
    }
}

public class App {
    public static void main(String[] args) {
        Logger logger = new Logger();
        logger.startLogging();

        // Simulating multiple threads generating log messages
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                logger.log("Log message from " + Thread.currentThread().getName());
            }).start();
        }
    }
}
