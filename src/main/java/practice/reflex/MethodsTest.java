package practice.reflex;

import java.lang.reflect.Method;

/**
 * @Author lvrongzhuan
 * @desc:反射方法
 * @createTime 2018/2/27 17:07
 * @Modified by:
 */
public class MethodsTest {
    public static void main(String[] args) throws Exception {
        //1.获取Class对象
        Class stuClass = Student.class;
        //2.获取所有公有方法
        System.out.println("***************获取所有的”公有“方法包括父类的*******************");
        stuClass.getMethods();
        Method[] methodArray = stuClass.getMethods();
        for(Method m : methodArray){
//            System.out.println(m);
            System.out.println("getDeclaringClass:"+m.getDeclaringClass()+"  methodName:"+m.getName());
        }
        System.out.println("***************获取所有的方法，包括私有的*******************");
        methodArray = stuClass.getDeclaredMethods();
        for(Method m : methodArray){
            System.out.println(m);
        }
        System.out.println("***************获取公有的show1()方法*******************");
        Method m = stuClass.getMethod("show1", String.class);
        System.out.println(m);
        //实例化一个Student对象
        Object obj = stuClass.getConstructor().newInstance();
        m.invoke(obj, "刘德华");

        System.out.println("***************获取私有的show4()方法******************");
        m = stuClass.getDeclaredMethod("show4", int.class);
        System.out.println(m);
        m.setAccessible(true);//解除私有限定
        Object result = m.invoke(obj, 20);//需要两个参数，一个是要调用的对象（获取有反射），一个是实参
        System.out.println("返回值：" + result);

        //反射main方法
        Method main = stuClass.getMethod("main",String[].class);
        main.invoke(null,(Object)new String[]{"a"});


    }
}
