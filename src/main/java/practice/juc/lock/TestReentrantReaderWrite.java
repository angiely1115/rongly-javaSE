package practice.juc.lock;

import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @Author: lvrongzhuan
 * @Description:读写锁 读读共享 读写互斥 写写互斥
 * @Date: 2018/7/24 11:27
 * @Version: 1.0
 * modified by:
 */
public class TestReentrantReaderWrite {
    private ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    ReentrantReadWriteLock.ReadLock readLock = readWriteLock.readLock();
    ReentrantReadWriteLock.WriteLock writeLock = readWriteLock.writeLock();

    public void read(){
        readLock.lock();
        System.out.println(Thread.currentThread().getName()+"开始读");
        try {
            TimeUnit.SECONDS.sleep(1);
            System.out.println(Thread.currentThread().getName()+"读完毕");
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            readLock.unlock();
        }
    }

    public void write(){
        writeLock.lock();
        System.out.println(Thread.currentThread().getName()+"开始写");
        try {
            TimeUnit.SECONDS.sleep(1);
            System.out.println(Thread.currentThread().getName()+"写完毕");
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            writeLock.unlock();
        }
    }

    public static void main(String[] args) {
//        CopyOnWriteArrayList
        TestReentrantReaderWrite urrw = new TestReentrantReaderWrite();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                urrw.read();
//                urrw.write();
            }
        }, "t1");
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                urrw.read();
//                urrw.write();
            }
        }, "t2");
        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                urrw.write();
            }
        }, "t3");
        Thread t4 = new Thread(new Runnable() {
            @Override
            public void run() {
                urrw.write();
            }
        }, "t4");

        //读读共享
//        t1.start();
//        t2.start();

        //读写互斥
//      t1.start(); // R
//      t3.start(); // W

        //写写互斥
      t3.start();
      t4.start();
    }

}
