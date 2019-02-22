package practice.ClassLoadTest;


import practice.ClassLoadTest.Negative.NegativeReference0;
import practice.ClassLoadTest.myClassLoad.Parent2;

import java.util.List;
import java.util.Random;

/**
 * @Author: lvrongzhuan
 * @Description: 类加载器
 * @Date: 2018/10/15 13:02
 * @Version: 1.0
 * modified by:
 */
public class ClassLoadTest02 {
    public static void main(String[] args) throws ClassNotFoundException {
        //bootstrap 类加载器
        System.out.println(Object.class.getClassLoader());

        System.out.println(String.class.getClassLoader());

        System.out.println(List.class.getClassLoader());
        System.out.println(Parent2.class.getClassLoader());
        System.out.println(NegativeReference0.Parent.class.getClassLoader());
        Class clazz1 = Parent2.class;
        //会执行static代码块
        Class clazz12 = Class.forName(Parent2.class.getName());
        //不会执行static代码块
        Class clazz13 = ClassLoader.getSystemClassLoader().loadClass(Parent2.class.getName());
        System.out.println(clazz1==clazz12);
        System.out.println(clazz1==clazz13);

        String str = "www.atguigu.com" ;
        while(true)
        {
            str += str + new Random().nextInt(88888888) + new Random().nextInt(999999999) ;
        }

    }
}
