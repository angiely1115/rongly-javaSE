package practice.collections;

import jdk.management.resource.internal.inst.SocketOutputStreamRMHooks;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.stream.Stream;

/**
 * @Author: lvrongzhuan
 * @Description:
 * HashMap是数组加链表的数据结构存储，Node内部类是链表结构，维护了K和value以及Next下一个节点。
 * HashMap中维护了一个Node<K,V>[] table;Node节点的数组
 *hash算法的作用：为了给数据落在数组哪个下标节点上做前期准备工作，java8才用Hashcode的值高16位与低16位做异或运算
 * 为什么扩容一定要是2的n次幂，保证每个bin保持相同的索引位置。
 * 因为这样做会减少hash冲突，使得hash值分布均匀，提高效率。
 *
 *
 * @Date: 2018/7/26 0:46
 * @Version: 1.0
 * modified by:
 */
public class CollectionTest {
    @Test
    public void mapTest(){
        //如果自定义对象作为key 一定要重新equals和hashcode方法
        //定义容量 (预存储大小/0.75)+1
        //put的主要步骤:
        /**
         * 1.计算重新计算hash值
         * 2.第一次put 调用resize()方法进行扩容和扩容阀值的计算等
         * 3.计算hash值对应数组的下标，如果没有值就直接存放 有值比较hash值 放入链表中
         */
        HashMap<String,String> stringMap = new HashMap<>();
        stringMap.put("1","吕荣砖");
        stringMap.put("2","赵雅芝");
        stringMap.put("3","彭雪梅");
        stringMap.put("3","彭雪梅");
        stringMap.put(null,"SB");
        stringMap.forEach((x,y)-> System.out.println("key:"+x+" value:"+y));
        System.out.println(hash("0"));
        stringMap.get("3");
        stringMap.replace("3","hah");
        stringMap.remove("3");
        for (int binCount = 0; ; ++binCount){
            System.out.println("binCount:"+binCount);
        }

    }


     int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }

    /**
     * concurrentHashMap 分析
     */
    @Test
    public void concurrentHashMapTest() {
        ConcurrentHashMap<String,String> stringConcurrentHashMap = new ConcurrentHashMap<>();
        stringConcurrentHashMap.put("1","1");
    }
    static class MyMap extends HashMap{
        public void myMapDemo(){
            MyMap myMap = new MyMap();
            myMap.clear();
        }
    }
    /**
     * 经过源码分析 创建list的时候最好自定容量大小，如果不指定虽然默认容量是10，也是经过一次数组拷贝。
     */
    @Test
    public void listTest(){
        ArrayList<String> arrays = new ArrayList<>(3);
        arrays.add("1");
        arrays.add("1");
        arrays.add("1");
//        Arrays.asList() 必须要包装类，生产的list并不是ArrayList 而是Arrays中一个名为ArrayList的内部类
        String[] sts = new String[arrays.size()];
        //转换数组使用定义好的数组 避免强制转换
        arrays.toArray(sts);
        Vector vector = new Vector();
        //底层是也是一个object[]数组，并且被volatile和修饰transient，自然也有自己的readObject和writeObject方法来实现序列化
        //通过ReentrantLock 来实现线程安全，add remove、replace、set等写操作上锁并且都是同一个锁，对get(int i)方法没有锁
        /**
         * 疑问1:对get方法没有锁，怎么保证现在安全？
         * 因为保存数据的array是被volatile修饰的，在写数据前会增加一个store内存屏障，强制把值把刷新到主内存中，
         * 在读操作前增加load屏障，读取主内存的值。
         *疑问2:为什么每增加一个新元素，都要进行一次数组的复制消耗，那为何不每次不将数组的元素设大（比如说像ArrayList那样，设置为原来的1.5倍+1），
         * 这样就会大大减少因为数组元素复制所带来的消耗？
         */
        CopyOnWriteArrayList copyOnWriteArrayList = new CopyOnWriteArrayList();

        //单向链表
        LinkedList<String> stringList = new LinkedList<>();
//        stringList.subList(1,3); 此新的list操作都会影响源list的值

    }

    public class MyVector extends Vector{
        public  void main(String[] args) {
            MyVector myVector = new MyVector();
           Object[] objects = myVector.elementData;
        }
    }

    public void setTest(){
        /**
         * HashSet 内部是实际是一个hashMap 被transient修饰，自然也有自己的readObject和writeObject方法来实现序列化
         * hashset add 方法就是hashMap的put操作 所以hashset所有的特征觉得定hashMap的特征
         */
        Set<String> stringSet = new HashSet<>();

        /**
         * LinkedHashSet继承hashset 内部使用的linkedhashMap 主要保证了元素插入的顺序。
         * 那么实际上我们也可以通过HashSet的
         * HashSet(int initialCapacity, float loadFactor, boolean dummy) {
         map = new LinkedHashMap<>(initialCapacity, loadFactor); 构造函数生产LinkdeHashSet
         }
         */
        LinkedHashSet linkedHashSet = new LinkedHashSet();

        /**
         * 内部就是通过CopyOnWriteArrayList来实现
         */
        CopyOnWriteArraySet copyOnWriteArraySet = new CopyOnWriteArraySet();

    }

    @Test
    public void collectionsSetMap(){
        Map<String,Boolean> stringMap = new HashMap<>();
        Collections.newSetFromMap(stringMap);
    }

    @Test
    public void toArrayTest(){
        List<String> strings = new ArrayList<>(3);
        strings.add("1");
        strings.add("2");
        strings.add("d");
        strings.add("e");
        String[] strings1 = strings.toArray(new String[0]);
        Stream.of(strings1).forEach(v-> System.out.println(v));
       Optional<String> stringOptional = Stream.of(strings1).map(s->s.toUpperCase()).findAny();
       String[] ss = Stream.of(strings1).flatMap(s->{
           System.out.println("flatMap:"+s);
          return Stream.of(getStrs(s));
       }).toArray(String[]::new);
        Stream.of(ss).forEach(v-> System.out.println("ss:"+v));



    }

    public String[] getStrs(String s){
        return new String[]{s.toUpperCase()};
    }
}
