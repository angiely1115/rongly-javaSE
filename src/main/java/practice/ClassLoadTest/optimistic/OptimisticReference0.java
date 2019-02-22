package practice.ClassLoadTest.optimistic;

/**
 * 类加载主动引用之main方法在初始类中
 * 主动引用
 * 如果一个类被主动引用，就会触发类的初始化。在java中，主动引用的情况有：
 * 1 通过new关键字实例化对象、读取或设置类的静态变量、调用类的静态方法。
 * 2 通过反射方式执行以上三种行为。
 * 3 初始化子类的时候，会触发父类的初始化。
 * 4 作为程序入口直接运行时（也就是直接调用main方法）。
 */
public class OptimisticReference0 {
    static {
        System.out.println(OptimisticReference0.class.getName() + " is referred!");
    }

    public static void main(String[] args) {
        System.out.println();
    }
}
