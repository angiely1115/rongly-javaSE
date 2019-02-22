package practice.juc;

/**
 * @Author: lvrongzhuan
 * @Description:
 * 传递的是引用，线程的副本，
 * ThreadLocalMap内部类
 * @Date: 2018/10/6 11:53
 * @Version: 1.0
 * modified by:
 */
public class TestThreadLocal {
    static ThreadLocal<String> stringThreadLocal = new ThreadLocal<String>(){
        @Override
        protected String initialValue() {
           return "pxm";
        }
    };
    public static void main(String[] args) {
        stringThreadLocal.remove();
    }
}
