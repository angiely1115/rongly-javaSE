package practice.lambda;

import com.sun.org.apache.regexp.internal.RE;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.Year;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

/**
 * @Author: lvrongzhuan
 * @Description: 通过java8 lambda流式运算 将list转map 过滤、分组、求和等
 * @Date: 2019/3/17 11:05
 * @Version: 1.0
 * modified by:
 */
public class ListToMapTest {
    private static String string = "static";

    /**
     * 静态内部类只能访问外部的静态属性和静态方法
     * 非静态内部类不能创建静态成员变量和静态方法
     */
    @Data
    @AllArgsConstructor
    public static class User {
        private String userName;
        private int age;

        public void inner() {
            System.out.println(string);
        }

    }

    static List<User> list = new ArrayList<User>();

    @BeforeAll
    public static void initData() {

        User u1 = new User("pangHu", 26);
        User u2 = new User("piKaQiu", 15);
        User u3 = new User("laoBi", 20);
        User u4 = new User("2019-09", 20);
        User u5 = new User("2019-10", 26);
        list.add(u1);
        list.add(u2);
        list.add(u3);
        list.add(u4);
        list.add(u5);
    }

    @Test
    public void testToMap1() {

        //转换成一个新流
        list.stream().map(User::getUserName).forEach(System.out::println);
        //转换成一个map  key 是userName value 是User对象
        Map map = null;
//        map = list.stream().collect(Collectors.toMap(User::getUserName,b->b)); //有重复的key就抛异常?
        System.out.println(map);
        map = list.stream().collect(Collectors.toMap(User::getUserName, Function.identity(), (k1, k2) -> {
            System.out.println("k1:" + k1 + " k2:" + k2);
            return k1;
        }));
        System.out.println(map);

        //分组
       Map<String,List<User>> treeMap = list.stream().collect(Collectors.groupingBy(User::getUserName));
        TreeMap<String,List<User>> listTreeMap = new TreeMap<>((o1, o2) -> o2.compareTo(o1));
        listTreeMap.putAll(treeMap);
        System.out.println("treeMap:"+listTreeMap);
        //多级分组
        map = list.stream().collect(Collectors.groupingBy(User::getUserName, Collectors.groupingBy(e -> {
            if (e.getAge() > 60) {
                return "老年";
            } else if (e.getAge() > 40) {
                return "中老年";
            } else if (e.getAge() > 20) {
                return "青年";
            } else {
                return "少年";
            }
        })));
        System.out.println(map);

        //求和
        int sumAge = list.stream().mapToInt(User::getAge).sum();
        System.out.println("年龄和：" + sumAge);

        list.stream().map(User::getAge).reduce(0, (x, y) -> {
            return x + y;
        });

        Long count = list.stream().collect(Collectors.counting());

        System.out.println(count);

        //分割 加前后缀
        String str = list.stream()
                .map(User::getUserName)
                .collect(Collectors.joining(",", "----", "----"));

        System.out.println(str);

        //排序 按年龄找出最大的元素 如果有多个年龄最大值相等 返回第一个
        Optional<User> optionalUser = list.stream().max(Comparator.comparing(x -> x.getAge()));
        optionalUser.ifPresent(System.out::println);
        optionalUser = list.stream().min(Comparator.comparing(x -> x.getAge()));
        optionalUser.ifPresent(System.out::println);
        List list1 = list.stream().sorted(Comparator.comparing(u -> u.getAge(), (x, y) -> {
            return Integer.valueOf(y).compareTo(Integer.valueOf(x));
        })).collect(toList());
        System.out.println(list1);

        System.out.println(list.stream().map(User::getAge).collect(toList()));

        System.out.println(BigDecimal.ZERO.subtract(BigDecimal.valueOf(1223.06)));
        System.out.println(BigDecimal.ZERO);
    }

    /**
     * 测试排序 默认升序
     */
    @Test
    public void testSorted() {
        list.stream().sorted(Comparator.comparing(User::getAge)).collect(Collectors.toList()).forEach(System.out::println);
    }

    /**
     * 测试 flatMap 偏平处理多个流
     */
    @Test
    public void testFlatMap() {
        List<String> list = new ArrayList<>();
        list.add("aaa bbb ccc");
        list.add("ddd eee fff");
        list.add("ggg hhh iii");
        list.stream().map(s -> s.split(" ")).flatMap(Arrays::stream).collect(toList()).forEach(System.out::println);
    }

    /**
     * 测试 intStream
     * 因为如果将流转成map会增加装箱拆箱的操作 对性能有一定的影响 可以是intStream操作 使用都是基本类型
     * int sum = list.stream().map(Person::getAge).reduce(0, Integer::sum);
     * 计算元素总和的方法其中暗含了装箱成本，map(Person::getAge) 方法过后流变成了 Stream 类型，而每个 Integer 都要拆箱成一个原始类型再进行 sum 方法求和，这样大大影响了效率。
     * 针对这个问题 Java 8 有良心地引入了数值流 IntStream, DoubleStream, LongStream，
     * 这种流中的元素都是原始数据类型，分别是 int，double，long
     * 但是对BigDecimal还无法使用这种方式
     */
    @Test
    public void testIntStream() {

        System.out.println(IntStream.rangeClosed(1, 100000000).sum());
        ;

    }

    public  <E> List<E> getDuplicateElements(List<E> list) {
        // 获得元素出现频率的 Map，键为元素，值为元素出现的次数
        return list.stream() // list 对应的 Stream
                .collect(Collectors.toMap(e -> e, e -> 1, (a, b) -> a + b))
                .entrySet().stream() // 所有 entry 对应的 Stream
                .filter(entry -> entry.getValue() > 1) // 过滤出元素出现次数大于 1 的 entry
                .map(entry -> entry.getKey()) // 获得 entry 的键（重复元素）对应的 Stream
                .collect(Collectors.toList());  // 转化为 List
    }

    @Test
    public  void testListRepeat(){
        List<String> list = Arrays.asList("a", "b", "c", "d", "a", "a", "d", "d");
        Set<Map.Entry<String, Integer>> stringMap = list.stream().collect(Collectors.toMap(o -> o, o ->1,(a, b) -> a+b )).entrySet();
        System.out.println(stringMap);
        List<String> duplicateElements = getDuplicateElements(list);

        System.out.println("list 中重复的元素：" + duplicateElements);
    }
}
