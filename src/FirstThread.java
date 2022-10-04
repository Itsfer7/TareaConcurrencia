import java.util.Vector;
import java.util.concurrent.Semaphore;

public class FirstThread implements Runnable {
    private final int times;
    private final Semaphore sendPackageSemaphore;
    private final Semaphore receivePackageSemaphore;
    private final Vector<Integer> packageList;

    public FirstThread(int times, Semaphore sendPackageSemaphore, Semaphore receivePackageSemaphore, Vector<Integer> packageList) {
        this.times = times;
        this.sendPackageSemaphore = sendPackageSemaphore;
        this.receivePackageSemaphore = receivePackageSemaphore;
        this.packageList = packageList;
    }

    @Override
    public void run() {
        for (int i = 1; i <= times; i++) {
            try {
                sendPackageSemaphore.acquire();
                System.out.println("Enviando paquete " + i);
                packageList.add(i);
                receivePackageSemaphore.release();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}