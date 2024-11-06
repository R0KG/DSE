import java.util.PriorityQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CyclicBarrier;

public class Merge implements Runnable {
    private customBlockQueue<Integer> inpQueue, outQueue;

    private PriorityQueue<Integer> sortedSet;

    public Merge(customBlockQueue<Integer> inpQueue, customBlockQueue<Integer> outQueue) {
        this.inpQueue = inpQueue;
        this.outQueue = outQueue;

        this.sortedSet = new PriorityQueue<>();
    }

    @Override
    public void run() {
        try {
            while (true) {
                // Wait until all Multiply threads reach the barrier

                Thread.sleep(500);
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

            }
        } catch (Exception e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }
}
