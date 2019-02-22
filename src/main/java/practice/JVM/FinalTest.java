package practice.JVM;

/**
 * @Author: lvrongzhuan
 * @Description:
 * @Date: 2018/10/17 16:23
 * @Version: 1.0
 * modified by:
 */
public class FinalTest {

    private static final String a = null;

    public static void final01(final int b){
        final String c ="2";
        /*
        不能修改
        b = 1;
        c = "1";*/
        final int d =1;
    }
}
