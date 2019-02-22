package practice.JVM;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: lvrongzhuan
 * @Description:直接内存溢出 一般是出现在使用了NIO的情况，并且dump文件也没有什么信息
 * @Date: 2018/10/16 19:15
 * @Version: 1.0
 * modified by:
 */
public class DirectMemory {
    public static void main(String[] args) {
        List<Object> objects = new ArrayList<>();
        while(true){
            objects.add(ByteBuffer.allocateDirect(1024*1024));
        }

    }
}
