import java.util.Vector;
import java.util.concurrent.Semaphore;

public class Main {
    public static void main(String[] args) {

        Vector<Integer> packageList = new Vector<>();

        Semaphore fullListSemaphore = new Semaphore(0);
        Semaphore emptyListSemaphore = new Semaphore(1);

        Thread firstThread = new Thread(new FirstThread(100, fullListSemaphore, emptyListSemaphore, packageList));
        Thread secondThread = new Thread(new SecondThread(100, fullListSemaphore, emptyListSemaphore, packageList));

        firstThread.start();
        secondThread.start();
    }
}