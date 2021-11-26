public class Producer implements Runnable{

    public static final int MAX_CARS = 10;
    public static final int TIME_TO_LAST_MSG = 4000;
    CarStore store;


    Producer(CarStore store) {
        this.store = store;
    }

    public void run() {
        for (int i = 1; i <= MAX_CARS; i++) {
            store.putCar();
        }
        try {
            Thread.sleep(TIME_TO_LAST_MSG);
            System.out.println("Машины больше не производятся.До завтра");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
