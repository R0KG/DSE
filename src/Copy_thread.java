import java.util.concurrent.BlockingQueue;

public class Copy_thread implements Runnable {
    private final customBlockQueue<Integer> inp_queue, queue2, queue3, queue5;
    private final customBlockQueue<Integer> printqueue;

    public Copy_thread(customBlockQueue<Integer> inp_queue, customBlockQueue<Integer> queue2, customBlockQueue<Integer> queue3, customBlockQueue<Integer> queue5, customBlockQueue<Integer> printqueue) {
        this.inp_queue = inp_queue;
        this.queue2 = queue2;
        this.queue3 = queue3;
        this.queue5 = queue5;
        this.printqueue = printqueue;
    }

    @Override
    public void run() {
        try {
            while (true) {
                int num = inp_queue.take(); // Take the smallest Hamming number from copyQueue
                queue2.put(num); // Copy it to each of the output queues
                queue3.put(num);
                queue5.put(num);
                printqueue.put(num);
                Thread.sleep(500);
            }
        } catch (Exception e) {
            Thread.currentThread().interrupt();
        }
    }
}
