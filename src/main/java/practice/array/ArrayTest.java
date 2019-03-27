package practice.array;


import com.google.common.collect.Lists;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

/**
 * @Author: lvrongzhuan
 * @Description:
 * @Date: 2018/12/27 17:24
 * @Version: 1.0
 * modified by:
 */
public class ArrayTest {
    public static void main(String[] args) {
        int[] ints = new int[10];
        System.out.println(ints.getClass());
        int i = 1;
        System.out.println(int.class);
        System.out.println(double.class.getConstructors());
        System.out.println(long.class);
        System.out.println(Long.class);
        System.out.println(void.class);
    }

    /**
     * list集合转数组
     */
    @Test
    public void listToArray() {
        List<Person> stringList = Lists.newArrayList();
        stringList.add(new Person("朋友"));
        stringList.add(new Person("彭雪梅"));
        stringList.add(new Person("白素贞"));
        stringList.add(new Person("吕荣砖"));
        // 泛型丢失无法使用string类型接收参数 是一个浅拷贝
        Object[] objects = stringList.toArray();
        objects[2] = "彭SB";
        System.out.println(Arrays.asList(objects));
        System.out.println(stringList);
        // 转换时数组的大小等于集合的大小
        Person[] peoples = new Person[stringList.size()];
        peoples = stringList.toArray(peoples);
        System.out.println(Arrays.asList(peoples));
//        peoples = new Person[4];
//        stringList.toArray(peoples);
//        System.out.println(Arrays.asList(peoples));



    }

    class Person {
        private String name;

        public Person(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "Person{" +
                    "name='" + name + '\'' +
                    '}';
        }
    }

}
