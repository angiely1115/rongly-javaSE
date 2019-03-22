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
        /**
         * 即使该类有多个子类 实例化多个子类时候也只会执行一次
         */
        static {
            System.out.println(Parent.class.getSimpleName() + " is referred!");
        }
    }

    public static class Child extends Parent {
        static {
            System.out.println(Child.class.getSimpleName() + " is referred!");
        }
    }
    public static class Chil2 extends Parent {
        static {
            System.out.println(Chil2.class.getSimpleName() + " is referred!");
        }
    }

    public static void main(String[] args) {
        new Child();
        new Chil2();
        new Parent();
    }
}