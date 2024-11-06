import java.util.concurrent.BlockingQueue;

public class Print implements Runnable {
    private customBlockQueue<Integer>  inp_query;

    public Print(customBlockQueue<Integer> inp_query) {
        this.inp_query = inp_query;
    }

    @Override
    public void run(){
        try {
            while (true){
                System.out.println("Hamming number: " + inp_query.take());
            }
        }
        catch (InterruptedException e){
            Thread.currentThread().interrupt();
        }
    }

}
