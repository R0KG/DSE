import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CyclicBarrier;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        // Queues for each stage


        BlockingQueue<Integer> copyQueue = new ArrayBlockingQueue<>(10);
        BlockingQueue<Integer> queue2 = new ArrayBlockingQueue<>(10);
        BlockingQueue<Integer> queue3 = new ArrayBlockingQueue<>(10);
        BlockingQueue<Integer> queue5 = new ArrayBlockingQueue<>(10);
        BlockingQueue<Integer> mergeQueue = new ArrayBlockingQueue<>(10);
        BlockingQueue<Integer> printqueue = new ArrayBlockingQueue<>(10);
        CyclicBarrier barrier = new CyclicBarrier(4);
        //CountDownLatch latch = new CountDownLatch(3);

        // Start the threads
        copyQueue.add(1);
        new Thread(new Copy_thread(copyQueue, queue2, queue3, queue5,printqueue)).start();
        new Thread(new Multiply(2, queue2, mergeQueue,barrier)).start();
        new Thread(new Multiply(3, queue3, mergeQueue,barrier)).start();
        new Thread(new Multiply(5, queue5, mergeQueue,barrier)).start();
        new Thread(new Merge(mergeQueue, copyQueue,barrier)).start();

        // latch.await();


        new Thread(new Print(printqueue)).start();



    }
}
