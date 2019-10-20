package practice.algorithm.queue;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Author: lvrongzhuan
 * @Description: 数组实现队列
 * @Date: 2019/10/17 21:01
 * @Version: 1.0
 * modified by:
 */
public class ArrayQueue<T> {
    // 队列大小
    private int maxSize;
    // 队列头
    private int head;
    // 队列尾
    private int foot;
    // 队列
    private Object[] arr;

    public ArrayQueue(int maxSize) {
        this.maxSize = maxSize;
        arr = new Object[maxSize];
        head = -1;
        foot = -1;
    }

    public static void main(String[] args) {
        ArrayQueue<Person> personArrayQueue = new ArrayQueue<>(10);
        personArrayQueue.add(new Person("pxm"));
        personArrayQueue.add(new Person("lvrz"));
        System.out.println(personArrayQueue.get());
        System.out.println(personArrayQueue.get());
        System.out.println(personArrayQueue.get());
    }
    public boolean isEmpty() {
        return foot==head;
    }
    public boolean isFull() {
        return foot==maxSize-1;
    }

    public void add(T t) {
        if (isFull()) {
            throw new RuntimeException("队列已经满");
        } else {
            foot++;
            arr[foot] = t;
        }
    }

    public T get() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空");
        }
        head++;
        return (T) arr[head];
    }
    @AllArgsConstructor
    @Data
   static class Person{
        private String name;
    }
}
