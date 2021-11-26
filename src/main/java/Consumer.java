public class Consumer implements Runnable{

    CarStore store;
    public static final int WAITING_TIME = 1500;
    public static final int CARS_BUY = 4;

    public Consumer(CarStore store) {
        this.store = store;

    }
    public void run() {

        for (int i = 1; i < CARS_BUY; i++) {
            store.buyCar();
            try {
                Thread.sleep(WAITING_TIME);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
