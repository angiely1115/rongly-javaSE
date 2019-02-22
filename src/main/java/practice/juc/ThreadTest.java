package practice.juc;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @Author: lvrongzhuan
 * @Description:
 * @Date: 2018/9/28 10:57
 * @Version: 1.0
 * modified by:
 */
public class ThreadTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ThreadTest threadTest = new ThreadTest();
        threadTest.testThread();
        threadTest.testCompletableFuture();
    }

    @Test
    public void testThread(){
        new Thread(()->{
            System.out.println(Thread.currentThread().getName());
            try {
                Thread.sleep(3000);
                this.hh();
                System.out.println(Thread.currentThread().getName()+"end");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        System.out.println(Thread.currentThread().getName());
     /*   try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        new Thread(()->{
            System.out.println(Thread.currentThread().getName());
        }).start();
    }

    private void hh(){
        System.out.println("kkkkkkkkk");
    }

    @Test
    public void testCompletableFuture() throws ExecutionException, InterruptedException {
        CompletableFuture completableFuture=CompletableFuture.runAsync(()->{
            System.out.println(Thread.currentThread().getName());
            try {
                System.out.println(Thread.currentThread().getName()+"sleep start");
                TimeUnit.MILLISECONDS.sleep(300);
                System.out.println(Thread.currentThread().getName()+"end");

//                return "completableFuture";
            } catch (InterruptedException e) {
                e.printStackTrace();
//                return "InterruptedException";
            }
        });
        //如果不获取结果sleep后不会执行
       // System.out.println("get:"+completableFuture.get());
    }

    /**
     * 如果没有意外，上面发的代码工作得很正常。但是，如果任务执行过程中产生了异常会怎样呢？
     非常不幸，这种情况下你会得到一个相当糟糕的结果：异常会被限制在执行任务的线程的范围内，
     最终会杀死该线程，而这会导致等待 get 方法返回结果的线程永久地被阻塞。
     客户端可以使用重载版本的 get 方法，它使用一个超时参数来避免发生这样的情况。
     这是一种值得推荐的做法，你应该尽量在你的代码中添加超时判断的逻辑，避免发生类似的问题。
     使用这种方法至少能防止程序永久地等待下去，超时发生时，程序会得到通知发生了 Timeout-Exception 。
     不过，也因为如此，你不能指定执行任务的线程内到底发生了什么问题。
     为了能获取任务线程内发生的异常，你需要使用
     CompletableFuture 的completeExceptionally方法将导致CompletableFuture 内发生问题的异常抛出。
     这样，当执行任务发生异常时，调用get()方法的线程将会收到一个 ExecutionException 异常，
     该异常接收了一个包含失败原因的Exception 参数。
     * CompletableFuture 异常处理
     * @throws Exception
     */
    @Test
    public  void test2() throws Exception{
        CompletableFuture<String> completableFuture=new CompletableFuture();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    //模拟执行耗时任务
                    System.out.println("task doing..."+Thread.currentThread().getName());
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    throw new RuntimeException("抛异常了");
                }catch (Exception e) {
                    e.printStackTrace();
                    //告诉completableFuture任务发生异常了
//                    completableFuture.completeExceptionally(e);
                }
            }
        }).start();
        //获取任务结果，如果没有完成有异常没有处理并且没有设置获取结果等待时间会一直阻塞等待
        String result=completableFuture.get(10,TimeUnit.SECONDS);
        System.out.println("计算结果:"+result);
    }

    /**
     *allOf 工厂方法接收一个由CompletableFuture 构成的数组，数组中的所有 Completable-Future 对象执行完成之后，
     * 它返回一个 CompletableFuture<Void> 对象。这意味着，如果你需要等待多个 CompletableFuture 对象执行完毕，
     * 对 allOf 方法返回的CompletableFuture 执行 join 操作可以等待CompletableFuture执行完成。

     或者你可能希望只要 CompletableFuture 对象数组中有任何一个执行完毕就不再等待，在这种情况下，你可以使用一个类似的工厂方法 anyOf 。

     该方法接收一个 CompletableFuture 对象构成的数组，返回由第一个执行完毕的 CompletableFuture 对象的返回值构成的 CompletableFuture<Object> 。

     作者：不迷失
     链接：https://www.jianshu.com/p/4897ccdcb278
     來源：简书
     简书著作权归作者所有，任何形式的转载都请联系作者获得授权并注明出处。
     * @throws Exception
     */
    @Test
    public  void test4() throws Exception {
        CompletableFuture<String> completableFuture1=CompletableFuture.supplyAsync(()->{
            //模拟执行耗时任务
            System.out.println("task1 doing..."+Thread.currentThread().getName());
            try {
                Thread.sleep(2100);
                throw new RuntimeException("我要抛异常");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //返回结果
            return "result1";
        });

        CompletableFuture<String> completableFuture2=CompletableFuture.supplyAsync(()->{
            //模拟执行耗时任务
            System.out.println("task2 doing..."+Thread.currentThread().getName());
           /* try {
                Thread.sleep(4200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/
            //返回结果
            return "result2";
        });
        //CompletableFuture<Object> anyResult=CompletableFuture.anyOf(completableFuture1,completableFuture2);
       // System.out.println("第一个完成的任务结果:"+anyResult.get()+" "+Thread.currentThread().getName());

        CompletableFuture<Void> allResult=CompletableFuture.allOf(completableFuture1,completableFuture2);
        //阻塞等待所有任务执行完成
        allResult.join();
        System.out.println("第一个完成的任务结果:"+completableFuture1.get()+" "+Thread.currentThread().getName());
        System.out.println("第二个完成的任务结果:"+completableFuture2.get()+" "+Thread.currentThread().getName());
        System.out.println("所有任务执行完成");

    }
    /**
     * 将两个CompletableFuture建立联系
     通常，我们会有多个需要独立运行但又有所依赖的的任务。比如先等用于的订单处理完毕然后才发送邮件通知客户。
     thenCompose 方法允许你对两个异步操作进行流水线，第一个操作完成时，将其结果作为参数传递给第二个操作。
     你可以创建两个CompletableFutures 对象，
     对第一个 CompletableFuture 对象调用thenCompose ，并向其传递一个函数。
     当第一个CompletableFuture 执行完毕后，它的结果将作为该函数的参数，
     这个函数的返回值是以第一个 CompletableFuture 的返回做输入计算出的第二个 CompletableFuture 对象。
     作者：不迷失
     链接：https://www.jianshu.com/p/4897ccdcb278
     來源：简书
     简书著作权归作者所有，任何形式的转载都请联系作者获得授权并注明出处。
     * @throws Exception
     */
    @Test
    public  void test5() throws Exception {

        CompletableFuture<String> completableFuture1=CompletableFuture.supplyAsync(()->{
            //模拟执行耗时任务
            System.out.println("task1 doing..."+Thread.currentThread().getName());
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //返回结果
            return "result1";
        });

        //等第一个任务完成后，将任务结果传给参数result，执行后面的任务并返回一个代表任务的completableFuture
        CompletableFuture<String> completableFuture2= completableFuture1 .thenCompose(result->CompletableFuture.supplyAsync(()->{
            //模拟执行耗时任务
            System.out.println("task2 doing..."+Thread.currentThread().getName());
            try {
                Thread.sleep(3000);
                System.out.println("task1 result:"+result);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //返回结果
            return "result2";
        }));

        System.out.println(completableFuture2.get()+Thread.currentThread().getName());
        //如果不获取结果 线程马上停止

    }
    /**
     * 另一种比较常见的情况是，你需要将两个完
     全不相干的 CompletableFuture 对象的结果整合起来，而且你也不希望等到第一个任务完全结
     束才开始第二项任务。
     这种情况，你应该使用 thenCombine 方法，它接收名为 BiFunction 的第二参数，这个参数
     定义了当两个 CompletableFuture 对象完成计算后，结果如何合并。
     作者：不迷失
     链接：https://www.jianshu.com/p/4897ccdcb278
     來源：简书
     简书著作权归作者所有，任何形式的转载都请联系作者获得授权并注明出处。
     * @throws Exception
     */
    @Test
    public  void test6() throws Exception {
        CompletableFuture<Integer> completableFuture1 = CompletableFuture.supplyAsync(() -> {
            //模拟执行耗时任务
            System.out.println("task1 doing..."+Thread.currentThread().getName());
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //返回结果
            return 100;
        });

        //将第一个任务与第二个任务组合一起执行，都执行完成后，将两个任务的结果合并
        CompletableFuture<Integer> completableFuture2 = completableFuture1.thenCombine(
                //第二个任务
                CompletableFuture.supplyAsync(() -> {
                    //模拟执行耗时任务
                    System.out.println("task2 doing..."+Thread.currentThread().getName());
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    //返回结果
                    return 2000;
                }),
                //合并函数
                (result5, result6) -> result5 + result6);

        System.out.println(completableFuture2.get());

    }

    /**
     * 响应 CompletableFuture 的 completion 事件
     我们可以在每个CompletableFuture 上注册一个操作，该操作会在 CompletableFuture 完成执行后调用它。
     CompletableFuture 通过 thenAccept 方法提供了这一功能，它接收
     CompletableFuture 执行完毕后的返回值做参数。
     作者：不迷失
     链接：https://www.jianshu.com/p/4897ccdcb278
     來源：简书
     简书著作权归作者所有，任何形式的转载都请联系作者获得授权并注明出处。
     * @throws Exception
     */
    @Test
    public  void test7() throws Exception {

        CompletableFuture<Integer> completableFuture1 = CompletableFuture.supplyAsync(() -> {
            //模拟执行耗时任务
            System.out.println("task1 doing..."+Thread.currentThread().getName());
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //返回结果
            return 100;
        });

        //注册完成事件
        completableFuture1.thenAccept(result->System.out.println("task1 done,result:"+Thread.currentThread().getName()+" result "+result));
        //注册异步完成事件 但是感觉还是同一个线程
        completableFuture1.thenAcceptAsync(result->System.out.println("task1 done,result:"+Thread.currentThread().getName()+" result "+result));
        CompletableFuture<Integer> completableFuture2=
                //第二个任务
                CompletableFuture.supplyAsync(() -> {
                    //模拟执行耗时任务
                    System.out.println("task2 doing..."+Thread.currentThread().getName());
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    //返回结果
                    return 2000;
                });

        //注册完成事件
        completableFuture2.thenAccept(result->System.out.println("task2 done,result:"+Thread.currentThread().getName()+" result"+result));

        //将第一个任务与第二个任务组合一起执行，都执行完成后，将两个任务的结果合并
        CompletableFuture<Integer> completableFuture3 = completableFuture1.thenCombine(completableFuture2,
                //合并函数
                (result1, result2) -> {
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return result1 + result2;
                });

        System.out.println(completableFuture3.get());

    }
}
