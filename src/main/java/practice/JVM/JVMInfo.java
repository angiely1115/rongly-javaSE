package practice.JVM;

import org.junit.jupiter.api.Test;

import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.util.List;

/**
 * @Author: lvrongzhuan
 * @Description:获取jvm配置信息
 * @Date: 2018/10/17 18:56
 * @Version: 1.0
 * modified by:
 */
public class JVMInfo {

    @Test
    public void getJvmInfo(){
        List<GarbageCollectorMXBean> garbageCollectorMXBeans =  ManagementFactory.getGarbageCollectorMXBeans();
        garbageCollectorMXBeans.stream().forEach((garbageCollectorMXBean->{
            System.out.println(garbageCollectorMXBean.getName());
        }));

        System.out.println(ManagementFactory.getClassLoadingMXBean().getLoadedClassCount());
    }
}
