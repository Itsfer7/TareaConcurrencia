import java.util.Vector;
import java.util.concurrent.Semaphore;

public class SecondThread implements Runnable {
    private final int times;
    private final Semaphore fullListSemaphore;
    private final Semaphore emptyListSemaphore;
    private final Vector<Integer> packageList;


    public SecondThread(int times, Semaphore fullListSemaphore, Semaphore emptyListSemaphore, Vector<Integer> packageList) {
        this.times = times;
        this.fullListSemaphore = fullListSemaphore;
        this.emptyListSemaphore = emptyListSemaphore;
        this.packageList = packageList;
    }

    @Override
    public void run() {
        for (int i = 1; i <= times; i++) {
            try {
                fullListSemaphore.acquire();
                if (packageList.size() == 0) {
                    emptyListSemaphore.acquire();
                    System.out.println("Recibiendo paquete " + i);
                } else {
                    fullListSemaphore.release();
                }
                emptyListSemaphore.release();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("Programa terminado");
    }
}
