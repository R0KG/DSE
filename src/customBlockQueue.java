import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class customBlockQueue<T> {
    private final LinkedList<T> queue = new LinkedList<>();
    private final Set<T> uniqueElem = new HashSet<>();
    private final int maxSize;
    private final int wait_N;
    private final AtomicInteger count = new AtomicInteger(0);



    public customBlockQueue(int maxSize, int N) {
        this.maxSize = maxSize;
        this.wait_N = N;
    }

    public synchronized void put(T item) throws InterruptedException {
        while (queue.size() == maxSize) {
            wait();
        }

        if (uniqueElem.add(item)){
            if (count.incrementAndGet() == wait_N) {
                uniqueElem.clear();
                queue.add(item);
                count.set(0);
                notifyAll();
            }
        }

        System.out.println(Thread.currentThread().getName() + ": added " + item + " to queue");




    }


    public synchronized T take() throws InterruptedException {
        while (queue.isEmpty()) {
            wait();
        }
        T item = queue.removeFirst();
        System.out.println(Thread.currentThread().getName() + ": removed " + item + " from queue");
        notifyAll();
        return item;
    }

    public synchronized void add(T item) throws InterruptedException {
        put(item);
    }

    public synchronized boolean isEmpty() throws InterruptedException {
        return queue.isEmpty();
    }
}
