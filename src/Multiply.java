import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

public class Multiply implements Runnable {
    private BlockingQueue<Integer> inp;
    private BlockingQueue<Integer> out;
    private int multiplicator;
    private final CyclicBarrier barrier;


    public Multiply(int multiplicator, BlockingQueue<Integer> inp, BlockingQueue<Integer> out , CyclicBarrier barrier) {
        this.inp = inp;
        this.out = out;
        this.multiplicator = multiplicator;
        this.barrier = barrier;

    }

    @Override
    public void run() {
        try {

            while (true) {
                int num = inp.take(); // Take a number from the input queue
                int result = num * multiplicator; // Multiply it by the given multiplicator
                out.put(result); // Place the result into the output queue

                barrier.await();
            }
        } catch (Exception e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }
}
