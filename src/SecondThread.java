import java.util.Vector;
import java.util.concurrent.Semaphore;

public class SecondThread implements Runnable {
    private final int times;
    private final Semaphore sendPackageSemaphore;
    private final Semaphore receivePackageSemaphore;
    private final Vector<Integer> packageList;


    public SecondThread(int times, Semaphore sendPackageSemaphore, Semaphore receivePackageSemaphore, Vector<Integer> packageList) {
        this.times = times;
        this.sendPackageSemaphore = sendPackageSemaphore;
        this.receivePackageSemaphore = receivePackageSemaphore;
        this.packageList = packageList;
    }

    @Override
    public void run() {
        for (int i = 1; i <= times; i++) {
            try {
                receivePackageSemaphore.acquire();
                int num = packageList.remove(0);
                System.out.println("Recibiendo paquete " + num);
                sendPackageSemaphore.release();
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("Programa terminado");
    }
}
