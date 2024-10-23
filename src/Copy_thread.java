import java.util.concurrent.BlockingQueue;

public class Copy_thread implements Runnable {
    private BlockingQueue<Integer> queue2,queue3,queue5;
    private int num;

    public Copy_thread(BlockingQueue<Integer> queue2,BlockingQueue<Integer> queue3,BlockingQueue<Integer> queue5,int num ) {
        this.queue2 = queue2;
        this.queue3 = queue3;
        this.queue5 = queue5;
        this.num = num;
    }


    @Override
    public void run(){
        try {
            queue2.put(num);
            queue3.put(num);
            queue5.put(num);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
