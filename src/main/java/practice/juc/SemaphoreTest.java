package practice.juc;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

class EatThread extends Thread{
    private Semaphore semaphore;
    public EatThread(Semaphore semaphore){
        this.semaphore=semaphore;
    }
    @Override
    public void run(){
        try {
            semaphore.acquire();//获取一个许可，当然也可以调用acquire(int)，这样一个线程就能拿到多个许可
            long eatTime=(long) (Math.random()*10);
            System.out.println(Thread.currentThread().getId()+" 正在吃饭");
            TimeUnit.SECONDS.sleep(eatTime);
            System.out.println(Thread.currentThread().getId()+" 已经吃完");
            System.out.println("可用剩余可用许可："+semaphore.availablePermits());
            semaphore.release();//归还许可
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
public class SemaphoreTest {    
    public static void main(String[] args) {
        Semaphore semaphore=new Semaphore(5);//总共有5个许可
        for(int i=0;i<7;i++){//定义七个吃的线程
            new EatThread(semaphore).start();
        }
    }
}