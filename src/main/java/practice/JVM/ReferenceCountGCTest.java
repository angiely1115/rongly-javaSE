package practice.JVM;

/**
 * @Author: lvrongzhuan
 * @Description: 引用计数器GC判断
 * @Date: 2018/10/15 17:05
 * @Version: 1.0
 * modified by:
 */
public class ReferenceCountGCTest {

    //定义一个2M的数据
    private byte[] bytes = new byte[1024*2];

    private ReferenceCountGCTest instance;

    public static  void test(){
        ReferenceCountGCTest re1 = new ReferenceCountGCTest();
        ReferenceCountGCTest re2 = new ReferenceCountGCTest();
        re1.instance = re2;
        re2.instance = re1;
        re1 = null;
        re2 = null;
        System.gc();
    }

    public static void main(String[] args) {
        ReferenceCountGCTest.test();
    }

}
