package practice.ClassLoadTest.optimistic;

/**
 * 类加载主动引用之子类初始化会触发父类初始化
 * 而且先初始化父类
 */
public class OptimisticReference1 {
    static {
        System.out.println(OptimisticReference1.class.getSimpleName() + " is referred!");
    }
    public static class Parent {
        static {
            System.out.println(Parent.class.getSimpleName() + " is referred!");
        }
    }

    public static class Child extends Parent {
        static {
            System.out.println(Child.class.getSimpleName() + " is referred!");
        }
    }

    public static void main(String[] args) {
        new Child();
    }
}