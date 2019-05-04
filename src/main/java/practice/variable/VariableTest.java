package practice.variable;

import org.junit.jupiter.api.Test;

/**
 * @Author: lvrongzhuan
 * @Description: java中变量测试
 * java变量分为 实例变量 类变量 和局部变量
 * @Date: 2019/4/25 13:58
 * @Version: 1.0
 * modified by:
 */
public class VariableTest {

    //定义一个实例变量
     String x = "Parent`s Instance Variable";

    /**
     * 打印实例变量
     */
    public void printInstanceVariable() {
        System.out.println(x);
    }

    /**
     * 阴影变量
     */
    @Test
    public void printLocalVariable() {
        // Shadowing instance variable `x` with a local variable with the same name
        String x = "局部变量";
        System.out.println(x);
        // 使用this来访问
      // If we still want to access the instance variable, we do that by using `this.x`
        System.out.println("this.x:" + this.x);
    }

    /**
     * 隐藏变量
     */
    @Test
    public void testHidingVariable() {
        new Child().printLocalVariable();
    }

    class Child extends VariableTest{
       // Hiding the Parent class's variable `x` by defining a variable in the child class with the same name.
        String x = "Child`s Instance Variable";
        @Override
        public void printLocalVariable() {
            String x = "子类的局部变量";
            System.out.println(x);
            System.out.println("this.x:"+this.x);
            System.out.println("super.x:"+super.x);
        }
    }


}
