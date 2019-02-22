package practice.TestString;

import org.junit.jupiter.api.Test;

/**
 * @Author lvrongzhuan
 * @desc: String 类相关操作 不可变性 线程安全
 * @createTime 2018/2/27 14:16
 * @Modified by:
 */
public class StringTest {
    @Test
    public void Test1(){
        //String直接拼接
        String s3 = new StringBuffer().append("a").append("b").toString();
        s3.intern();
        String s1 = "a"+"b";
        String s2 = "ab";
        String s4 = "a".concat("b");
        s4 = s4.intern();
        System.out.println("s1==s2:"+(s1==s2));
        System.out.println("s1==s3:"+(s1==s3));
        System.out.println("s2==s3:"+(s2==s3));
        System.out.println("s1==s4:"+(s1==s4));
        s2.substring(2);

        System.out.println("几号库："+8393990%12/4);
        System.out.println("几号表："+8393990%12%4);
    }
}
