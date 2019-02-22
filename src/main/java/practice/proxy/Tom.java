package practice.proxy;

import java.io.Serializable;

/**
 * @Author lvrongzhuan
 * @desc:
 * @createTime 2017/12/1818:55
 * @Modified by:
 */
public class Tom implements People,Serializable{
    @Override
    public void jiehun(String name) {
        System.out.println("我是程序员，写的一手好代码，但是没有撩人的技能。".concat(name));
    }

    @Override
    public void jiehun() {
        System.out.println("你是小笨蛋");
    }
}
