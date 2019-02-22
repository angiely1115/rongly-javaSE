package practice.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @Author lvrongzhuan
 * @desc: tom的父母
 * @createTime 2017/12/1818:59
 * @Modified by:
 */
public class TomFM implements InvocationHandler{
    People people;

    public TomFM(People people) {
        this.people = people;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        this.before();
        method.invoke(people,args);
        this.after();
        return null;
    }

    public void  before(){
        System.out.println("我们是tom老师的父母，我们在帮他介绍对象");
    }

    public void after(){
        System.out.println("结婚后帮他带孩子");
    }
}
