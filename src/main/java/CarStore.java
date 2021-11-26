import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class CarStore {

    ReentrantLock locker;
    Condition condition;
    Car car = new Car();

    public static final int THREAD_SLEEP_TIME = 1000;
    public static final int ASSEMBLY_TIME = 1000;
    public static final int MAX_DELIVERY = 1;

    List<Car> cars = new ArrayList<>();

    CarStore(){
        locker = new ReentrantLock(); // создаем блокировку
        condition = locker.newCondition(); // получаем условие, связанное с блокировкой
    }


    public void buyCar() {

        try {
            locker.lock();
            System.out.println(Thread.currentThread().getName() + " зашел в салон");
            while (cars.size() == 0) {
                System.out.println("Машин нет. Ждите");
                condition.await();
            }
            Thread.sleep(THREAD_SLEEP_TIME);
            System.out.println(Thread.currentThread().getName() + " Уехал на " + car.getName());
            cars.remove(0);
            condition.signalAll();
            System.out.println("Товаров на складе: " + cars.size());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            locker.unlock();
        }
    }

    public void putCar() {

        try {
            locker.lock();
            while (cars.size() >= MAX_DELIVERY) {
                condition.await();
            }
            Thread.sleep(ASSEMBLY_TIME);
            cars.add(new Car());
            System.out.println("Производитель TESLA привез 1 автомобиль");
            System.out.println("Товаров на складе: " + cars.size());
            condition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            locker.unlock();
        }
    }
}