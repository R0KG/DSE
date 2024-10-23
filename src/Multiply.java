import java.util.concurrent.BlockingQueue;

public class Multiply implements Runnable {
    private BlockingQueue<Integer> inp, outp;
    private int multiplicator;

    public Multiply(BlockingQueue<Integer> inp, BlockingQueue<Integer> outp, int multiplicator) {
        this.inp = inp;
        this.outp = outp;
        this.multiplicator = multiplicator;
    }

    @Override
    public void run() {
        try {
            while (true) {
                int num= inp.take();
                outp.put(num * multiplicator);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();

        }
    }
}
