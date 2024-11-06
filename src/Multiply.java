import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CyclicBarrier;

public class Multiply implements Runnable {
    private customBlockQueue<Integer> inp;
    private customBlockQueue<Integer> out;
    private int multiplicator;
    private final CyclicBarrier barrier;


    public Multiply(int multiplicator, customBlockQueue<Integer> inp, customBlockQueue<Integer> out , CyclicBarrier barrier) {
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
                Thread.sleep(500);
            }
        } catch (Exception e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }
}
