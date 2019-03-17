package practice.lambda;

import com.sun.org.apache.regexp.internal.RE;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.Year;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

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
        private  String userName;
        private int age;

        public void inner() {
            System.out.println(string);
        }

    }

    @Test
    public void  testToMap1() {
        List<User> list = new ArrayList<User>();
        User u1 = new User("pangHu", 26);
        User u2 = new User("piKaQiu", 15);
        User u3 = new User("laoBi", 20);
        User u4 = new User("wangHao", 20);
        User u5 = new User("wangHao", 26);
        list.add(u1);
        list.add(u2);
        list.add(u3);
        list.add(u4);
        list.add(u5);
        //转换成一个新流
        list.stream().map(User::getUserName).forEach(System.out::println);
        //转换成一个map  key 是userName value 是User对象
        Map map = null;
//        map = list.stream().collect(Collectors.toMap(User::getUserName,User::getAge));
        System.out.println(map);
        map = list.stream().collect(Collectors.toMap(User::getUserName, Function.identity(), (k1, k2) ->{
            System.out.println("k1:"+k1 +" k2:"+k2);
            return k1;
        }));
        System.out.println(map);

        //分组
        map = list.stream().collect(Collectors.groupingBy(User::getUserName));
        System.out.println(map);
        //多级分组
     map =   list.stream().collect(Collectors.groupingBy(User::getUserName, Collectors.groupingBy(e ->{
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
        int sumAge =  list.stream().mapToInt(User::getAge).sum();
        System.out.println("年龄和："+sumAge);

        list.stream().map(User::getAge).reduce(0, (x, y) ->{
            return x+y;
        } );

        Long count = list.stream()
                .collect(Collectors.counting());

        System.out.println(count);

        //分割 加前后缀
        String str = list.stream()
                .map(User::getUserName)
                .collect(Collectors.joining("," , "----", "----"));

        System.out.println(str);

        //排序 按年龄找出最大的元素 如果有多个年龄最大值相等 返回第一个
        Optional<User> optionalUser = list.stream().max(Comparator.comparing(x -> x.getAge()));
       optionalUser.ifPresent(System.out::println);
        optionalUser = list.stream().min(Comparator.comparing(x -> x.getAge()));
        optionalUser.ifPresent(System.out::println);
       List list1 = list.stream().sorted(Comparator.comparing(u->u.getAge(),(x,y)->{
           return Integer.valueOf(y).compareTo(Integer.valueOf(x));
       })).collect(Collectors.toList());
        System.out.println(list1);

    }
}
