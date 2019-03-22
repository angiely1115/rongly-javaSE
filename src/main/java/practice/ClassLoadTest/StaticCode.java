package practice.ClassLoadTest;

public class StaticCode {
    static String prior = "done";
    static String last = f() ? g() : prior;

    public static boolean f() {
        return true;
    }

    public static String g() {
        return "hello world ";
    }

    static {
        System.out.println("静态代码块");
        System.out.println(last);
        g();
    }

    static {
        _i = 10;
    }

    // 这里输出20
    static int _i = 30;
    static {
        _i = 20;
    }
    // 这里输出30 static int _i = 30;

    public static void main(String[] args) {
        System.out.println(_i);
    }
}