package lock;

import java.util.concurrent.atomic.AtomicReference;

/**
 * 自旋锁实现 (当线程需要阻塞的时间很短 大量的线程阻塞需要从用户态切换到内核态进行线程的阻塞操作 线程切换浪费了大量cpu资源，自旋锁能够让线程不让出cpu时间自己进行忙等待)
 */
public class SpinLock {
    public AtomicReference<Thread> atomicReference = new AtomicReference<>();
    public static int num;
    public void lock() {
        Thread thread = Thread.currentThread();
        while (!atomicReference.compareAndSet(null, thread)){
            System.out.println("自旋中~~~~~");
        }
    }
    public void unlock() {
        Thread thread = Thread.currentThread();
        atomicReference.compareAndSet(thread, null);
    }

    public static void main(String[] args) {
        SpinLock spinLock = new SpinLock();

        for (int i = 0; i < 100; i++) {
            new Thread(new Test(spinLock)).start();
        }
        try {
            Thread.sleep(1000);
            System.out.println(num);
        } catch (InterruptedException e) {

        }
    }
}
class Test implements Runnable {
    private SpinLock spinLock;
    public Test(SpinLock spinLock) {
        this.spinLock = spinLock;
    }
    @Override
    public void run() {
        spinLock.lock();
        SpinLock.num ++;
        spinLock.unlock();
    }
}

