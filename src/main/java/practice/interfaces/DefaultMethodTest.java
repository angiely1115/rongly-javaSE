package practice.interfaces;

/**
 * @Author: lvrongzhuan
 * @Description: 接口默认方法和静态方法
 * @Date: 2019/3/22 18:02
 * @Version: 1.0
 * modified by:
 * 1、接口默认方法、静态方法可以有多个。
 *
 * 2、默认方法通过实例调用，静态方法通过接口名调用。
 *
 * 3、 default默认方法关键字只能用在接口中。
 *
 * 4、默认方法可以被继承，如果继承了多个接口，多个接口都定义了多个同样的默认方法，实现类需要重写默认方法不然会报错。
 *
 * 5、静态方法不能被继承及覆盖，所以只被具体所在的接口调用。
 */
public class DefaultMethodTest {
    public static void main(String[] args) {
        // 直接调用接口中的静态方法 不能被实现类覆盖
        MyInterface.myInterfaceStatic();

        MyInterface myInterface = new MyInterfaceImpl1();
        // 通过实例调用默认方法 可以被覆盖
        myInterface.myInterfaceDefault1();

        myInterface.myInterface();
    }
}

interface MyInterface {
    default void myInterfaceDefault1() {
        System.out.println("我的接口默认方法");
    }
    static void myInterfaceStatic() {
        System.out.println("我的接口静态方法");
    }

    void myInterface();
}

class MyInterfaceImpl1 implements MyInterface {

    @Override
    public void myInterface() {
        System.out.println("第一个接口实现类");
    }

}
