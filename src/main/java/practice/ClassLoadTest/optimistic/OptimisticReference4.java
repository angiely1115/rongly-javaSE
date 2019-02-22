package practice.ClassLoadTest.optimistic;

/**
 * 类加载主动引用之反射
 */
public class OptimisticReference4 {
    public static void main(String[] args) throws ClassNotFoundException {
        Class.forName(OptimisticReference3.Child.class.getName());
        //只会把类加载到JVM中，不会执行静态快
        ClassLoader.getSystemClassLoader().loadClass(OptimisticReference3.Child.class.getName());
    }
}