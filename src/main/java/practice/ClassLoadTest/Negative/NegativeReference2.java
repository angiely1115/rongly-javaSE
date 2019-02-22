package practice.ClassLoadTest.Negative;

/**
 * 类加载被动引用之访问类定义的静态常量不会被初始化
 * 不是final常量会被初始化
 */
public class NegativeReference2 {
    public static class Child {
        public static final String name = "Child";
        static {
            System.out.println(Child.class.getSimpleName() + " is referred!");
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
