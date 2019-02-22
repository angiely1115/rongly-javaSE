package practice.proxy;

/**
 * @Author: lvrongzhuan
 * @Description:
 * @Date: 2018/8/2 16:59
 * @Version: 1.0
 * modified by:
 */
public class Mic implements People{
    @Override
    public void jiehun(String name) {
        System.out.println("mic 要结婚："+name);
    }

    @Override
    public void jiehun() {
        System.out.println("mic 老实");

    }
}
