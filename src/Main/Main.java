package Main;

import java.util.concurrent.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Main {

    public  static int i;
    /**
     * 闭锁
     */
    public static CountDownLatch countDownLatch = new CountDownLatch(1);

    /**
     * 可重入锁
     */
    public static ReentrantLock reentrantLock = new ReentrantLock();

    /**
     * 信号量
     */
    public static Semaphore semaphore = new Semaphore(2);

    /**
     * 栅栏
     */
    public static CyclicBarrier cyclicBarrier = new CyclicBarrier(2, new Runnable() {
        @Override
        public void run() {
            System.out.println("go on together");
        }
    });
    public static Condition condition = reentrantLock.newCondition();


    public static void main(String[] args) throws InterruptedException, ExecutionException {

        //ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(3, 5, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingDeque<>(3));
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 3L, TimeUnit.SECONDS, new SynchronousQueue<>());
//        threadPoolExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        threadPoolExecutor.execute(new Test());
        Thread.sleep(1000);
        threadPoolExecutor.execute(new Test1());

        //threadPoolExecutor.execute(new Test());
//
//          threadPoolExecutor.execute(new Test1());
//        threadPoolExecutor.execute(new Test());
//        threadPoolExecutor.execute(new Test());
//        threadPoolExecutor.execute(new Test());
//        threadPoolExecutor.execute(new Test());
//        threadPoolExecutor.execute(new Test());
//        threadPoolExecutor.execute(new Test());
//        threadPoolExecutor.execute(new Test());
//        threadPoolExecutor.execute(new Test());
//        threadPoolExecutor.execute(new Test());
//        threadPoolExecutor.execute(new Test());
//        threadPoolExecutor.execute(new Test());
    }

}
class Test implements Runnable {
    @Override
    public void run() {
        try {
            System.out.println(Thread.currentThread().getName() + "开始");
            Main.reentrantLock.lock();
            Main.condition.await();
            System.out.println(Thread.currentThread().getName() + "结束");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        finally {
            Main.reentrantLock.unlock();
        }
    }
}

class Test1 implements Runnable {
    @Override
    public void run() {
            System.out.println(Thread.currentThread().getName() + "开始");
            Main.reentrantLock.lock();
            Main.condition.signalAll();
            System.out.println(Thread.currentThread().getName() + "结束");
            Main.reentrantLock.unlock();
    }
}
//-XX:+PrintGCDateStamps
//-XX:+PrintGCDetails
//-XX:+UseSerialGC
//-Xms20M
//-Xmn10M
//-Xmx20M