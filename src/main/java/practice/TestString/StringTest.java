package practice.TestString;

import com.google.common.collect.Lists;
import com.vip.vjtools.vjkit.collection.ListUtil;
import com.vip.vjtools.vjkit.text.MoreStringUtil;
import lombok.var;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.function.Try;

import javax.swing.plaf.ListUI;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author lvrongzhuan
 * @desc: String 类相关操作 不可变性 线程安全
 * @createTime 2018/2/27 14:16
 * @Modified by:
 */
public class StringTest {
    @Test
    public void Test1(){
        //String直接拼接
        String s3 = new StringBuffer().append("a").append("b").toString();
        s3.intern();
        String s1 = "a"+"b";
        String s2 = "ab";
        String s4 = "a".concat("b");
        s4 = s4.intern();
        System.out.println("s1==s2:"+(s1==s2));
        System.out.println("s1==s3:"+(s1==s3));
        System.out.println("s2==s3:"+(s2==s3));
        System.out.println("s1==s4:"+(s1==s4));
        s2.substring(2);

        System.out.println("几号库："+8393990%12/4);
        System.out.println("几号表："+8393990%12%4);

        try {
            System.out.println("执行try内容");
//            throw new RuntimeException("异常了");
        }finally {
            System.out.println("执行finally 内容");
        }
        System.out.println("执行其他内容");
    }

    @Test
    public void testInteger(){
        Integer integer = 128;
        Integer integer1 = 128;
        System.out.println(integer==integer1);
        testSwitchForInteger(1111);
        testSwitchForInteger(127);
        testSwitchForInteger(129);
    }

    private void testSwitchForInteger(Integer integer){
        switch (integer) {
            case 1111:
                System.out.println("1111");
                break;
            case 127:
                System.out.println("127");
                break;
             default:
                 System.out.println("default");
                 break;

        }
    }

    /**
     * 练习IDE的一些骚操作
     */
    public String testIDE() {
        String s = "";
        ArrayList<Object> objects = Lists.newArrayList();
        for (Object object : objects) {

        }
        for (int i = 0; i < objects.size(); i++) {

        }
        if (objects.size()>0) {

        }
        return s;
    }
}
