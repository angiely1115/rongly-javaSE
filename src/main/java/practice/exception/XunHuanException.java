package practice.exception;

import org.junit.jupiter.api.Test;

/**
 * @Author: lvrongzhuan
 * @Description:在循环体中出现了异常 测试
 * @Date: 2019/3/18 12:41
 * @Version: 1.0
 * modified by:
 */
public class XunHuanException {
    @Test
    public void test1() {
        for (int i = 0; i < 10; i++) {
            try {
                System.out.println(i);
                if (i == 5) {
                    throw new RuntimeException("异常啦");
                }
            } catch (Exception e) {
                System.out.println("异常处：" + i);
                //进行下一次循环
                continue;
            }
            System.out.println(i);
        }
    }

    @Test
    public void test2() {
        try {
            for (int i = 0; i < 10; i++) {

                System.out.println(i);
                if (i == 5) {
                    throw new RuntimeException("异常啦");
                }

                System.out.println(i);
            }
        } catch (Exception e) {
            System.out.println("异常处：");
        }
    }

}
