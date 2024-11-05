import java.util.concurrent.BlockingQueue;

public class Copy_thread implements Runnable {
    private final BlockingQueue<Integer> inp_queue, queue2, queue3, queue5;
    private final BlockingQueue<Integer> printqueue;

    public Copy_thread(BlockingQueue<Integer> inp_queue, BlockingQueue<Integer> queue2, BlockingQueue<Integer> queue3, BlockingQueue<Integer> queue5, BlockingQueue<Integer> printqueue) {
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
            }
        } catch (Exception e) {
            Thread.currentThread().interrupt();
        }
    }
}
