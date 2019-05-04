package practice.juc;

import org.junit.jupiter.api.Test;

/**
 * @Author: lvrongzhuan
 * @Description: 子线程异常捕获测试
 * @Date: 2018/10/29 15:57
 * @Version: 1.0
 * modified by:
 */
public class ThreadExceptionTest {

    @Test
    public void test01(){
        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler(){
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                System.out.println("异常的线程："+t.getName());
                e.printStackTrace();
            }
        });
        new Thread(()->{
            System.out.println("当前子线程执行："+Thread.currentThread().getName());
            throw new RuntimeException(Thread.currentThread().getName()+"异常了");
        }).start();

        new Thread(()->{
            System.out.println("当前子线程执行："+Thread.currentThread().getName());
            throw new RuntimeException(Thread.currentThread().getName()+"异常了");
        }).start();
    }

    @Test
    public void test02(){
       Thread t =  new Thread(()->{
            System.out.println("当前子线程执行："+Thread.currentThread().getName());
            throw new RuntimeException("异常了");
        });
       t.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
           @Override
           public void uncaughtException(Thread t, Throwable e) {
               System.out.println("自己处理异常的线程："+t.getName());
               e.printStackTrace();
           }
       });
       t.start();
    }
}
