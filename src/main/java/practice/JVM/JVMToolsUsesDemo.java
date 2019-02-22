package practice.JVM;

import org.junit.jupiter.api.Test;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @Author: lvrongzhuan
 * @Description:jvm可视化工具使用
 * @Date: 2018/10/16 16:03
 * @Version: 1.0
 * modified by:
 */
public class JVMToolsUsesDemo {
    public static void fullheap(int num) throws InterruptedException {
        byte[] bytes = new byte[1024*1024];
        List<Object> objects = new ArrayList<>();
        for(int i=0;i<num;i++){
            TimeUnit.SECONDS.sleep(5);
            objects.add(objects);
            System.out.println(1);
        }
    }

    public static void main(String[] args) throws InterruptedException {
//        fullheap(1000);
        createBusyThread();
        createWaitThread(new Object());
        Thread thread1 = deadLock(1,2);
        thread1.setName("deadLock_1");
        Thread thread2 = deadLock(2,1);
        thread2.setName("deadLock_2");
        thread1.start();
        thread2.start();
    }


    @Test
    public void testjvmTools2(){
        createBusyThread();
        createWaitThread("hah");
    }

    /**
     * 创建一个繁忙的线程
     */
    public static void createBusyThread(){
        Thread thread = new Thread(()->{
            while (true);
        });
        thread.setName("busy_thread");
        thread.start();
    }

    /**
     * 创建一个等待的线程
     */
    public static void createWaitThread(Object lock){
        Thread thread = new Thread(()->{
            try {
                synchronized (lock) {
                    lock.wait();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread.setName("WaitThread");
        thread.start();
    }

    public static Thread deadLock(int a,int b){
        Thread thread = new Thread(()->{
            for (int i=0;i<100;i++) {
                synchronized (Integer.valueOf(a)) {
                    System.out.println(Thread.currentThread().getName()+"Integer.valueOf(a)_"+i);
                    synchronized (Integer.valueOf(b)) {
                        System.out.println(Thread.currentThread().getName()+"Integer.valueOf(b)_"+i);
                    }
                }
            }
        });
       return thread;

    }
}
