import java.util.concurrent.BlockingQueue;

public class Copy_thread implements Runnable {
    private BlockingQueue<Integer> queue2,queue3,queue5;

    public Copy_thread(BlockingQueue<Integer> queue2,BlockingQueue<Integer> queue3,BlockingQueue<Integer> queue5) {
        this.queue2 = queue2;
        this.queue3 = queue3;
        this.queue5 = queue5;
    }


    @Override
    public void run(){
        try {
            int num = 1;

        }
    }
}
