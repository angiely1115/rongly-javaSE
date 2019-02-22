package practice.juc;

import java.util.Date;
import java.util.concurrent.*;

/**
 * @Author: lvrongzhuan
 * @Description:限流实现 最大允许100个线程
 * @Date: 2018/7/24 17:33
 * @Version: 1.0
 * modified by:
 */
public class SemaphoreXianliu {
    public static void main(String[] args) {
        Executor pool = Executors.newFixedThreadPool(100);
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
        Semaphore semaphore = new Semaphore(100);
        for (int i = 0; i < 100; i++) {
            final int num = i;
            pool.execute(() -> {
                for (; ; ) {
                    for (int j = 0; j < 200; j++) {
                        /*try {
                            TimeUnit.MILLISECONDS.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }*/
                        if (semaphore.tryAcquire()) {
                            callRpc(num, j);
                        } else {
                            //System.err.println("call fail");
                        }
                    }
                }
            });
        }
        //延长1s每隔1s执行一次
        executorService.scheduleAtFixedRate(()->{
                    System.out.println("availablePermits:release"+(100-semaphore.availablePermits()));
                    semaphore.release(100-semaphore.availablePermits());
                }
               ,1000,1000, TimeUnit.MILLISECONDS);
    }

    private static void callRpc(int num, int j) {
        System.out.println(String.format("%s - %s: %d %d", new Date(), Thread.currentThread(), num, j));
    }
}
