import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CyclicBarrier;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        // Queues for each stage


        customBlockQueue<Integer> copyQueue = new customBlockQueue<>(10,2);
        customBlockQueue<Integer> queue2 = new customBlockQueue<>(10,1);
        customBlockQueue<Integer> queue3 = new customBlockQueue<>(10,1);
        customBlockQueue<Integer> queue5 = new customBlockQueue<>(10,1);
        customBlockQueue<Integer> mergeQueue = new customBlockQueue<>(10,1);
        customBlockQueue<Integer> printQueue = new customBlockQueue<>(10,1);
        CyclicBarrier barrier = new CyclicBarrier(3);
        //CountDownLatch latch = new CountDownLatch(3);


        // Adding a list of numbers to the queue
        copyQueue.put(1);
        copyQueue.put(2);





        new Thread(new Copy_thread(copyQueue, queue2, queue3, queue5,printQueue)).start();
        new Thread(new Multiply(2, queue2, mergeQueue,barrier)).start();
        new Thread(new Multiply(3, queue3, mergeQueue,barrier)).start();
        new Thread(new Multiply(5, queue5, mergeQueue,barrier)).start();
        new Thread(new Merge(mergeQueue, copyQueue)).start();

        // latch.await();


        new Thread(new Print(printQueue)).start();



    }
}
