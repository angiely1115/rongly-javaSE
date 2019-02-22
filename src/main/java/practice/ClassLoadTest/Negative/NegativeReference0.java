package practice.ClassLoadTest.Negative;

/**
 * 类加载是被动引用-子类加载父类静态属性 子类不会被初始化
 * 被动引用
 * 1 引用父类的静态字段，只会引起父类的初始化，而不会引起子类的初始化。
 * 2 定义类数组，不会引起类的初始化。
 * 3 引用类的常量，不会引起类的初始化。
 */
public class NegativeReference0 {
    public static class Parent {
        public static String name = "Parent";
        static {
            System.out.println(Parent.class.getSimpleName() + " is referred!");
        }
    }

    public static class Child extends Parent {
        static {
            System.out.println(Child.class.getSimpleName() + " is referred!");
        }

        public static void main(String[] args) {
            System.out.println("子类");
        }
    }

    public static void main(String[] args) {
        System.out.println(Child.name);
    }
}
/*
作者：零壹技术栈
链接：https://juejin.im/post/5b4deaa3e51d4519596b7f27
来源：掘金
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。*/
