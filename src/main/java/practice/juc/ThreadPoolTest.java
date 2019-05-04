package practice.juc;

import org.junit.jupiter.api.Test;

import java.util.concurrent.*;

/**
 * @Author: lvrongzhuan
 * @Description: 线程池测试
 * @Date: 2019/5/3 9:47
 * @Version: 1.0
 * modified by:
 */
public class ThreadPoolTest {
    public static void main(String[] args) {
        retry:
        for (; ; ) {
            if (ThreadLocalRandom.current().nextBoolean()) {
                System.out.println("broker");
                break retry;
            }
            System.out.println("for");
            continue retry;
        }
        LinkedBlockingQueue<Runnable> linkedBlockingQueue =  new LinkedBlockingQueue<Runnable>(10);
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5,8,3, TimeUnit.SECONDS,linkedBlockingQueue);
        System.out.println("任务队列之前："+threadPoolExecutor.getPoolSize());
        threadPoolExecutor.submit(() ->{
            for (int i = 0; i < 11; i++) {
                System.out.println("任务队列前："+threadPoolExecutor.getPoolSize());
                    if (i % 2 == 0) {
                        throw new RuntimeException("故意异常了");
                    }
            }
        });
        System.out.println("任务队列之后："+threadPoolExecutor.getPoolSize());
        threadPoolExecutor.shutdown();
    }

    private void executeRun() {


    }
}
