import java.util.Vector;
import java.util.concurrent.Semaphore;

public class FirstThread implements Runnable {
    private final int times;
    private final Semaphore fullListSemaphore;
    private final Semaphore emptyListSemaphore;
    private final Vector<Integer> packageList;

    public FirstThread(int times, Semaphore fullListSemaphore, Semaphore emptyListSemaphore, Vector<Integer> packageList) {
        this.times = times;
        this.fullListSemaphore = fullListSemaphore;
        this.emptyListSemaphore = emptyListSemaphore;
        this.packageList = packageList;
    }

    @Override
    public void run() {
        for (int i = 1; i <= times; i++) {
            try {
                emptyListSemaphore.acquire();
                if (packageList.size() == 3) {
                    fullListSemaphore.acquire();
                    packageList.add(i);
                    System.out.println("Enviando paquete " + i);
                } else {
                    emptyListSemaphore.release();
                }
                fullListSemaphore.release();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}