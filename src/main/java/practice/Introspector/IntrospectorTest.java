package practice.Introspector;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @Author lvrongzhuan
 * @desc:java反省测试
 * @createTime 2018/2/2810:45
 * @Modified by:
 */
public class IntrospectorTest {

    private User user;

    public void initUser(){
        user = new User("白娘子","杭州西湖",1000);
    }

    @Test
    public void getBeanPropertyInfo(){
        try {
            user = new User("白娘子","杭州西湖",1000);
           BeanInfo beanInfo = Introspector.getBeanInfo(User.class);
           PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            Arrays.stream(propertyDescriptors).forEach(p-> {
                System.out.println("pname:"+p.getName());
                Method method = p.getReadMethod() ;
                try {
                    System.out.println("mName:"+method.getName());
                    Object propertyValue = method.invoke(user);
                    System.out.println("pvalue:"+propertyValue);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
