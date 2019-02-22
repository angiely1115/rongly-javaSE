package practice.ClassLoadTest.Negative;

/**
 * 类加载被动引用之定义数组引用而不赋值
 */
public class NegativeReference1 {
    public static class Child {
        static {
            System.out.println(Child.class.getSimpleName() + " is referred!");
        }
    }

    public static void main(String[] args) {
        Child[] childs = new Child[10];
    }
}

/*
作者：零壹技术栈
链接：https://juejin.im/post/5b4deaa3e51d4519596b7f27
来源：掘金
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。*/
