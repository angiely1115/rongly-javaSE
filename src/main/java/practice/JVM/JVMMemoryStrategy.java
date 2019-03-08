package practice.JVM;

import org.junit.jupiter.api.Test;

import java.lang.management.ManagementFactory;
import java.util.List;

/**
 * @Author: lvrongzhuan
 * @Description: JVM内存分配策略
 * @Date: 2018/10/16 11:25
 * @Version: 1.0
 * modified by:
 */
public class JVMMemoryStrategy {
    /**
     * 优先新生代分配
     */
    private int init_memory = 1024*1024;
    @Test
    public void test1Eean(){
        byte[] byte1,byte2,byte3,byte4;
        byte1 = new byte[2*init_memory];//分配2M
        byte2 = new byte[2*init_memory];//分配2M
        byte3 = new byte[2*init_memory];//分配2M
        byte4 = new byte[4*init_memory];//分配4M
        /**
         * byte1 byte2 byte3 被分配到老年代
         * byte4 分配的新生代
         */
        List<String> vmArguments = ManagementFactory.getRuntimeMXBean().getInputArguments();
        System.out.println(vmArguments);

    }


}
