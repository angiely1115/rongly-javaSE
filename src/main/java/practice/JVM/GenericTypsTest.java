package practice.JVM;


import java.util.List;

/**
 * @Author: lvrongzhuan
 * @Description: 泛行擦除测试
 * @Date: 2018/10/17 16:29
 * @Version: 1.0
 * modified by:
 */
public class GenericTypsTest {

    /*public static void test1(List<String> stringList){
        //编译不通过 认为都是是同一个方法
        public static void test1(List<E> stringList)
    }*/

   /* public static int test1(List<String> stringList){
       //返回值不同，也是认为是同一个方法
        return 1;
    }*/

    public static String test1(List<Integer> stringList){
       return "";
    }

    public static void main(String[] args) {
        Integer a = 1;
        Integer b = 2;
        Integer c = 3;
        Integer d = 3;
        Integer e = 128;
        Integer f = 128;

        System.out.println(c==d);
        System.out.println(c==e);
        System.out.println(c==(a+b));
        System.out.println(c.equals(a+b));
        System.out.println(e==f);
        System.out.println(e.equals(f));
        if(true){
            System.out.println("e.equals(f)");
        }else{
            System.out.println("e.!equals(f)");
        }
    }
}
