package practice.juc.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author: lvrongzhuan
 * @Description:重入锁
 * @Date: 2018/7/24 10:33
 * @Version: 1.0
 * modified by:
 */
public class TestReentrantLock {
    private ReentrantLock reentrantLock = new ReentrantLock();
    public void method1(){
        reentrantLock.lock();
        System.out.println(Thread.currentThread().getName()+"进入method01");
        try {
            TimeUnit.SECONDS.sleep(1);
            System.out.println(Thread.currentThread().getName()+"退出method01");
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            System.out.println(Thread.currentThread().getName()+"释放锁");
            reentrantLock.unlock();
        }
    }
    public void method2(){
        reentrantLock.lock();
        System.out.println(Thread.currentThread().getName()+"进入method02");
        try {
            TimeUnit.SECONDS.sleep(1);
            System.out.println(Thread.currentThread().getName()+"退出method02");
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            System.out.println(Thread.currentThread().getName()+"释放锁");
            reentrantLock.unlock();
        }

    }

    public static void main(String[] args) {
        TestReentrantLock testReentrantLock = new TestReentrantLock();
     /*   for(int i=0;i<5;i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    testReentrantLock.method1();
                    testReentrantLock.method2();
                }
            }).start();
        }*/

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                testReentrantLock.method1();
            }
        }, "t1");
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                testReentrantLock.method2();
            }
        }, "t2");
        t1.start();
        t2.start();
    }
}
