package practice.singleton;

import java.nio.file.Path;

/**
 * @Author lvrongzhuan
 * @desc: 单例模式 静态类部类
 * @createTime 2018/3/3 14:05
 * @Modified by:
 */
public class SingletonTest {
    private SingletonTest() {
    }
    private static class SingletonClass{
       private static final SingletonTest singletonTest = new SingletonTest();
    }
    public static final SingletonTest getSingleton(){
        return SingletonClass.singletonTest;
    }

}
