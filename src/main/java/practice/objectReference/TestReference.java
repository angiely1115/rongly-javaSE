package practice.objectReference;

/**
 * @Author: lvrongzhuan
 * @Description:
 * @Date: 2018/11/16 21:16
 * @Version: 1.0
 * modified by:
 */
public class TestReference {
    public static void main(String[] args) {
        ReferenceDemo referenceDemo = new ReferenceDemo();
        referenceDemo.strong();
        referenceDemo.strong(referenceDemo);
    }
}
