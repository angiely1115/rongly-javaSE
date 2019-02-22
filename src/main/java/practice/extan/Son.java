package practice.extan;


/**
 * @Author lvrongzhuan
 * @desc:
 * @createTime 2018/3/615:58
 * @Modified by:
 */
public class Son extends Father{
    public Son() {
        System.out.println("儿子辈");
    }

    public static void main(String[] args) {
        new Son();
        new Girl();
        //是不是该类的子类 可以进进行转换的
        System.out.println(Girl.class.isAssignableFrom(Son.class));
    }
}
