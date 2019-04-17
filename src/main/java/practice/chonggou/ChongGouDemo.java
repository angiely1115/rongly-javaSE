package practice.chonggou;

/**
 * @Author: lvrongzhuan
 * @Description:
 * @Date: 2019/3/30 11:50
 * @Version: 1.0
 * modified by:
 */
public class ChongGouDemo {


    /**
     * 内联函数
     *
     * @return
     */
    int getRating() {
        int num = 0;
        return moreThanFiveLateDeliveries(num) ? 2 : 1;
    }

    /**
     * 内联局部临时变量
     *
     * @param num
     * @return
     */
    boolean moreThanFiveLateDeliveries(int num) {
        String str = "ooo".concat("kkk");
        String st2 = "ooo".concat("kkk").substring(2);
        return num > 5;
    }


    /**
     * 局部变量被多次使用赋值
     */
    public void splitValDemo() {

        double age = 10 / 9;
        System.out.println(age);
        age = age + 10;
        System.out.println(age);
        age = 90 * 90;
        System.out.println(age);
    }

    /**
     * 移除对参数的赋值
     * @param a
     * @param b
     * @param c
     */
    public int moveParamsAssignment(int a, int b, double c) {
        int atemp = new Acb(a, b).invoke();
        return atemp;
    }

    public void test1() {
        this.moveParamsAssignment(1,2,4.0);
    }


    private class Acb {
        private int a;
        private int b;

        public Acb(int a, int b) {
            this.a = a;
            this.b = b;
        }

        public int invoke() {
            int atemp = a;
            if (b > 10) {
                atemp = 10;
            }
            if (b > 20) {
                atemp = 20;
            }
            return atemp;
        }
    }
}
