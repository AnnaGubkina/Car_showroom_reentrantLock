

public class Main {
    public static final int MAX_CONSUMERS = 4;
    public static void main(String[] args) {


        final CarStore store = new CarStore();
        for (int i = 1; i <= MAX_CONSUMERS; i++) {
            new Thread(new Consumer(store), "Покупатель " + i).start();
        }

        new Thread(new Producer(store)).start();

    }
}
