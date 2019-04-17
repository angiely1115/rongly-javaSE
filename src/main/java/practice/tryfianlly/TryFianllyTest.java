package practice.tryfianlly;

import org.junit.jupiter.api.Test;

/**
 * @Author: lvrongzhuan
 * @Description: 论证 try 中return和finally 中谁先执行
 * 结论： 在try 中 return之后 结果返回之前执行
 * finally的作用不是改变原有变量的值，主要用来清理资源 释放连接 关闭管道
 *
 * @Date: 2019/3/22 18:15
 * @Version: 1.0
 * modified by:
 */
public class TryFianllyTest {

    @Test
    public void test01() {
        int i = this.test1();
        System.out.println("1最后返回值：" + i);
        System.out.println("----------------------------");
        i = this.test2();
        System.out.println("2最后返回值：" + i);
        System.out.println("----------------------------");
        i = this.test3();
        System.out.println("3最后返回值：" + i);
        System.out.println("----------------------------");
        i = this.test4();
        System.out.println("4最后返回值：" + i);
        System.out.println("----------------------------");
        i = this.test5();
        System.out.println("5最后返回值：" + i);
    }

    private int test1() {
        int i = 20;
        try {
            System.out.println("try brock");
            i += 80;
            return i;
        } finally {
            System.out.println("finally brock");
            if (i > 25) {
                System.out.println("i>25:" + i);
                //这里并没有改变i的最后返回值
                i -= 80;
            }
        }
    }

    private int test2() {
        int i = 20;
        try {
            System.out.println("try brock");
            i += 80;
            return i;
        } finally {
            System.out.println("finally brock");
            if (i > 25) {
                System.out.println("i>25:" + i);
                i -= 80;
                //这里return 会覆盖之前的值
                return i;
            }
        }
    }

    /**
     * 当有异常时候 最后的return会起到作用，没有异常时不会起作用
     * @return
     */
    private int test3() {
        int i = 20;
        try {
            System.out.println("try brock");
            i += 80;
            return i;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("finally brock");
            if (i > 25) {
                System.out.println("i>25:" + i);
                i -= 60;
            }
        }
        return i-=20;
    }

    /**
     * 如果有异常被catch住了并且被return了 那么就是catch中的返回值
     * @return
     */
    private int test4() {
        int i = 20;
        try {
            System.out.println("try brock");
            i += 80;
           int b = 2/0;
            return i;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("catch brock");
            return i+=15;
        } finally {
            System.out.println("finally brock");
            if (i > 25) {
                System.out.println("i>25:" + i);
                i -= 60;
            }
        }
    }
    private int test5() {
        int i = 20;
        try {
            System.out.println("try brock");
            i += 80;
            int b = 2/0;
            return i;
        } finally {
            System.out.println("finally brock");
            if (i > 25) {
                System.out.println("i>25:" + i);
                i -= 60;
            }
        }
    }
}
