package practice.ClassLoadTest.optimistic;

/**
 * 类加载主动引用之为静态变量赋值（也相当于访问静态变量）
 */
public class OptimisticReference3 {
    public static class Child {
        protected static String name;
        static {
            System.out.println(Child.class.getSimpleName() + " is referred!");
        }
        {
            //生成实例的时候才会触发
            System.out.println(Child.class.getSimpleName() + "普通代码块!");
        }
    }

    public static void main(String[] args) {
        Child.name = "Child";
    }
}

/*
作者：零壹技术栈
链接：https://juejin.im/post/5b4deaa3e51d4519596b7f27
来源：掘金
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。*/
