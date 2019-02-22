package practice.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Author lvrongzhuan
 * @desc:
 * @createTime 2017/12/18 18:46
 * @Modified by:
 */
public class JDKDynamicProxy implements InvocationHandler{

    private Object targerObject;

    public  Object creatProxyInstance(Object targerObject){
        this.targerObject = targerObject;
       return Proxy.newProxyInstance(targerObject.getClass().getClassLoader(),targerObject.getClass().getInterfaces(),this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
       return method.invoke(targerObject,args);
    }
}
