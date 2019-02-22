package practice.ClassLoadTest.myClassLoad;

import org.junit.jupiter.api.Test;

import javax.xml.crypto.dsig.spec.C14NMethodParameterSpec;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

/**
 * @Author: lvrongzhuan
 * @Description:
 * @Date: 2018/7/28 15:25
 * @Version: 1.0
 * modified by:
 */
public class CustomerClassLoaderTester {
    @Test
    public void test() throws Exception {
        // 创建自定义类加载器
        CustomClassLoader classLoader = new CustomClassLoader("D:\\resource\\workspace\\lvStudy\\out\\production\\JavaSE_Practice\\com\\lv\\javase\\practice\\ClassLoadTest"); // E://myclassloader//classpath
        // 动态加载class文件到内存中（无连接）
        Class<?> c = classLoader.loadClass("com.lv.javase.practice.ClassLoadTest.myClassLoad.Children");
        // 通过反射拿到所有的方法
        Method[] declaredMethods = c.getDeclaredMethods();
        for (Method method : declaredMethods) {
            if ("say".equals(method.getName())) {
                // 通过反射拿到children对象
                Object children = c.newInstance();
                // 调用children的say()方法
                method.invoke(children);
                break;
            }
        }
        CustomClassLoader diskLoader = new CustomClassLoader("D:\\resource\\workspace\\lvStudy\\out\\production\\JavaSE_Practice\\com\\lv\\javase\\practice\\ClassLoadTest");
        System.out.println("Thread "+Thread.currentThread().getName()+" classloader: "+Thread.currentThread().getContextClassLoader().toString());
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Thread "+Thread.currentThread().getName()+" classloader: "+Thread.currentThread().getContextClassLoader().toString());
                // TODO Auto-generated method stub
                try {
                    //加载class文件
                      Thread.currentThread().setContextClassLoader(diskLoader);
                   // Class c = diskLoader.loadClass("com.lv.javase.practice.ClassLoadTest.myClassLoad.Children");
                    ClassLoader cl = Thread.currentThread().getContextClassLoader();
                    System.out.println("Thread ClassLoader:"+cl);
                    Class c = cl.loadClass("com.lv.javase.practice.ClassLoadTest.myClassLoad.Children");
                    // Class c = Class.forName("com.frank.test.SpeakTest");
                    System.out.println(c.getClassLoader().toString());
                    if(c != null){
                        try {
                            Object obj = c.newInstance();
                            //SpeakTest1 speak = (SpeakTest1) obj;
                            //speak.speak();
                            Method method = c.getDeclaredMethod("say",null);
                            //通过反射调用Test类的say方法
                            method.invoke(obj, null);
                        } catch (InstantiationException | IllegalAccessException
                                | NoSuchMethodException
                                | SecurityException |
                                IllegalArgumentException |
                                InvocationTargetException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                } catch (ClassNotFoundException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }).start();
    }

}
