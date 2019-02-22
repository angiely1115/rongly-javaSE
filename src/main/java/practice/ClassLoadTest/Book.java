package practice.ClassLoadTest;

public class Book {
    public static void main(String[] args) {
        staticFunction();
    }


    /**
     * 本案例类加载顺序是
     * 1. 通过main会加载类构造器 就会初始化类静态代码块和类变量
     * 2. 此案例按照代码顺序先初始化类变量 static Book book = new Book(); 这时候就会初始化对象构造器
     * 就会执行普通代码块和构造方法，在构造方法由于代码顺序 amount值没有被加载初始化所以是0
     * 3.然后执行静态代码块
     * 4.执行调用的方法
     * 所以最后输出结果
     * 书的普通代码块
     * 书的构造方法
     * price=110,amount=0
     * 书的静态代码块
     * 书的静态方法
     */
    /**
     * 不是static 不会加载对象构造器 就不会执行普通代码快和构造函数
     */
    static Book book = new Book();
    static int amount = 112;

    static {
        System.out.println("书的静态代码块");
    }

    {
        System.out.println("书的普通代码块");
    }

    Book() {
        System.out.println("书的构造方法");
        System.out.println("price=" + price + ",amount=" + amount);
    }

    public static void staticFunction() {
        System.out.println("书的静态方法");
    }

    int price = 110;
}

