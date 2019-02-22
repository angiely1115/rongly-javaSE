package practice.proxy;

import sun.misc.ProxyGenerator;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Proxy;

/**
 * @Author lvrongzhuan
 * @desc:
 * @createTime 2017/12/1819:04
 * @Modified by:
 */
public class ProxyTest {
    public static void main(String[] args) {
        Tom tom = new Tom();
        People peopleProxy = (People) Proxy.newProxyInstance(tom.getClass().getClassLoader(),tom.getClass().getInterfaces(),new TomFM(tom));
        JDKDynamicProxy jdkDynamicProxy = new JDKDynamicProxy();
        People peopleProxy1 = (People) jdkDynamicProxy.creatProxyInstance(tom);
        peopleProxy1.jiehun();
        byte[] proxys = ProxyGenerator.generateProxyClass("$Proxy0",new Class[]{People.class});
        try(OutputStream outputStream = new FileOutputStream("F:\\1spring源码分析\\proxy.class")) {
            outputStream.write(proxys);
            outputStream.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
