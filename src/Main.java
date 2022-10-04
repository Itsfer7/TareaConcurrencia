import java.util.Vector;
import java.util.concurrent.Semaphore;

public class Main {
    public static void main(String[] args) {

        Vector<Integer> packageList = new Vector<>();

        Semaphore sendPackageSemaphore = new Semaphore(3);
        Semaphore receivePackageSemaphore = new Semaphore(0);

        Thread firstThread = new Thread(new FirstThread(100, sendPackageSemaphore, receivePackageSemaphore, packageList));
        Thread secondThread = new Thread(new SecondThread(100, sendPackageSemaphore, receivePackageSemaphore, packageList));

        firstThread.start();
        secondThread.start();
    }
}