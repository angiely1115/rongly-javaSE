package practice.juc;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author: lvrongzhuan
 * @Description:限流实现 最大允许100个线程
 * @Date: 2018/7/24 18:32
 * @Version: 1.0
 * modified by:
 */
public class AtomicIntegerXianliu {
    public static void main(String[] args) {
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
        AtomicInteger count = new AtomicInteger();
        executorService.scheduleAtFixedRate(()->count.getAndSet(100),1,1, TimeUnit.SECONDS);
        ExecutorService executor = Executors.newFixedThreadPool(100);
        for(int i=0;i<100;i++){
            final int num = i;
            executor.submit(() -> {
                for (; ; ) {
                    for (int j = 0; j < 200; j++) {
                        if (count.get() >= 0) {// 快速判断，否则大量的 CAS 操作将会定时任务更新计数器 count
                            if (count.decrementAndGet() >= 0) {
                                callRpc(num, j);
                            }
                        }
                    }
                }
            });
        }
    }
    private static void callRpc(int num, int j) {
        System.out.println(String.format("%s - %s: %d %d", new Date(), Thread.currentThread(), num, j));
    }
}
