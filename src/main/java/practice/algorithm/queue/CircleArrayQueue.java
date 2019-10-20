package practice.algorithm.queue;

import java.util.concurrent.ThreadLocalRandom;

/**
 * @Author: lvrongzhuan
 * @Description: 循环队列
 * @Date: 2019/10/17 22:23
 * @Version: 1.0
 * modified by:
 */
public class CircleArrayQueue<T> {
    private int maxSize;
    private int head;
    private int foot;
    private Object[] arrs;

    public CircleArrayQueue(int maxSize) {
        this.maxSize = maxSize;
        this.head = 0;
        this.foot = 0;
        this.arrs = new Object[maxSize];
    }

    public boolean isEmpty() {
        return head==foot;
    }

    public boolean isFull() {
        return (foot+1)%maxSize==head;
    }

    public void add(T t) {
        if (isFull()) {
            System.out.println("队列已满");
            return;
        }
        arrs[foot]=t;
        foot=(foot+1)%maxSize;
    }

    public T get() {
        if (isEmpty()) {
            System.out.println("队列为空");
            return null;
        }
        T t = (T) arrs[head];
        head = (head+1)%maxSize;
        return t;
    }

    public static void main(String[] args) {
        CircleArrayQueue<Integer> circleArrayQueue = new CircleArrayQueue<>(5);
        for (;;) {
            circleArrayQueue.add(ThreadLocalRandom.current().nextInt());
            System.out.println(circleArrayQueue.get());
        }

    }
}
