package practice.collections;

import com.google.common.collect.Lists;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @Author: lvrongzhuan
 * @Description: 多线程分段操作集合
 * @Date: 2019/5/8 10:57
 * @Version: 1.0
 * modified by:
 */
public class ThreadCollectionProcessor {
    public static void main(String[] args) throws Exception {

        // 开始时间
        long start = System.currentTimeMillis();
        List<String> list = new ArrayList<String>();

        for (int i = 1; i <= 30009; i++) {
            list.add(i + "");
        }
        // 每500条数据开启一条线程
        int threadProcessSize = 5000;
        // 总数据条数
        int dataSize = list.size();
        // 线程数
        int threadNum = dataSize / threadProcessSize + 1;
        System.out.println("线程数：" + threadNum);
        // 定义标记,过滤threadNum为整数
        boolean special = dataSize % threadProcessSize == 0;

        // 创建一个线程池
        ExecutorService exec = Executors.newFixedThreadPool(threadNum);
        // 定义一个任务集合
        List<Callable<Integer>> tasks = new ArrayList<Callable<Integer>>();
        Callable<Integer> task = null;
        List<String> cutList = null;

        // 确定每条线程的数据
        for (int i = 0; i < threadNum; i++) {
            if (i == threadNum - 1) {
                if (special) {
                    break;
                }
                cutList = list.subList(threadProcessSize * i, dataSize);
            } else {
                cutList = list.subList(threadProcessSize * i, threadProcessSize * (i + 1));
            }
            // System.out.println("第" + (i + 1) + "组：" + cutList.toString());
            final List<String> listStr = cutList;
            task = new Callable<Integer>() {

                @Override
                public Integer call() throws Exception {
                    System.out.println(Thread.currentThread().getName() + "线程：" + listStr);
                    return 1;
                }
            };
            // 这里提交的任务容器列表和返回的Future列表存在顺序对应的关系
            tasks.add(task);
        }

        List<Future<Integer>> results = exec.invokeAll(tasks);

        for (Future<Integer> future : results) {
            System.out.println(future.get());
        }

        // 关闭线程池
        exec.shutdown();
        System.out.println("线程任务执行结束");
        System.err.println("执行任务消耗了 ：" + (System.currentTimeMillis() - start) + "毫秒");
    }

    /**
     * 一个集合分段处理的数据起始行和结束行
     */
    @Test
    public void test() {
        List<String> stringList = Lists.newArrayList("1","2","3","4","5","6","7","8");
        int rowSize = stringList.size();
        //每个线程处理的数量
        int count = 2;
        //开启的线程数
        int runSize = (rowSize / count) + 1;
        boolean special = rowSize%count==0;
        CompletableFuture[] completableFutures = new CompletableFuture[runSize];
        for (int i = 0; i < runSize; i++) {
            int startIdx = 0;
            int endIdx = 0;
            if ((i + 1) == runSize) {
                if (special) {
                    break;
                }
                startIdx = (i * count);
                endIdx = rowSize;
            } else {
                startIdx = (i * count);
                endIdx = (i + 1) * count;
            }
            System.out.println("startIdx:" + startIdx + " endIdx :" + endIdx);
            System.out.println(stringList.subList(startIdx,endIdx));

        }

    }

}
