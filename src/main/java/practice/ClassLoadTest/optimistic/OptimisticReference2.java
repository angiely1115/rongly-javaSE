package practice.ClassLoadTest.optimistic;

/**
 * 类加载主动引用之访问类的一个静态变量
 */
public class OptimisticReference2 {
    public static class Child {
        protected static String name;
        static {
            System.out.println(Child.class.getSimpleName() + " is referred!");
            name = "Child";
        }
    }

    public static void main(String[] args) {
        System.out.println(Child.name);
//        System.out.println(new Child());
    }
}