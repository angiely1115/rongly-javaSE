package practice.juc.lock;

import com.sun.org.apache.regexp.internal.RE;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author: lvrongzhuan
 * @Description:
 * @Date: 2018/7/24 10:54
 * @Version: 1.0
 * modified by:
 */
public class TestCondition {
    private ReentrantLock reentrantLock = new ReentrantLock();
    Condition condition = reentrantLock.newCondition();

    public void method1() {
        reentrantLock.lock();
        System.out.println(Thread.currentThread().getName() + "进入method01");
        try {
            TimeUnit.SECONDS.sleep(1);
            System.out.println(Thread.currentThread().getName() + "进入method01等待");
            condition.await();
            System.out.println(Thread.currentThread().getName() + "退出method01");
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println(Thread.currentThread().getName() + "释放锁");
            reentrantLock.unlock();
        }
    }

    public void method2() {
        reentrantLock.lock();
        System.out.println(Thread.currentThread().getName() + "进入method02");
        try {
            TimeUnit.SECONDS.sleep(1);
            System.out.println("当前线程：" + Thread.currentThread().getName() + "发出唤醒..");
            condition.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println(Thread.currentThread().getName() + "释放锁");
            reentrantLock.unlock();
        }

    }

    public static void main(String[] args) {
        TestCondition tc = new TestCondition();
             for(int i=0;i<5;i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    tc.method1();
                    tc.method2();
                }
            }).start();
        }
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                tc.method1();
            }
        }, "t1");
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                tc.method2();
            }
        }, "t2");
        t1.start();
        t2.start();
    }
}
