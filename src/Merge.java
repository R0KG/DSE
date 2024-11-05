import java.util.PriorityQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CyclicBarrier;

public class Merge implements Runnable {
    private BlockingQueue<Integer> inpQueue, outQueue;
    private CyclicBarrier barrier;
    private PriorityQueue<Integer> sortedSet;

    public Merge(BlockingQueue<Integer> inpQueue, BlockingQueue<Integer> outQueue, CyclicBarrier barrier) {
        this.inpQueue = inpQueue;
        this.outQueue = outQueue;
        this.barrier = barrier;
        this.sortedSet = new PriorityQueue<>();
    }

    @Override
    public void run() {
        try {
            while (true) {
                // Wait until all Multiply threads reach the barrier


                // Collect numbers from inpQueue and maintain order
                while (!inpQueue.isEmpty()) {
                    int nextNumber = inpQueue.take();
                    if (!sortedSet.contains(nextNumber)) {
                        sortedSet.add(nextNumber);
                    }
                }

                // Output the smallest number to outQueue (copyQueue)
                if (!sortedSet.isEmpty()) {
                    int smallest = sortedSet.poll();
                    outQueue.put(smallest);

                }

                // Wait again at the barrier to sync with the next cycle
                barrier.await();
            }
        } catch (Exception e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }
}
