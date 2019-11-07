package practice.juc;

import com.google.common.collect.Maps;
import org.junit.jupiter.api.Test;

import java.util.Map;

/**
 * @Author: lvrongzhuan
 * @Description: 模拟hashmap使得cpu100%
 * @Date: 2019/11/6 16:11
 * @Version: 1.0
 * modified by:
 */
public class HashMapCpu100 {

    @Test
    public void concurrentMap100(){
        Map<String,String> map = Maps.newConcurrentMap();
        map.computeIfAbsent("AA",s -> map.put("BB","CC"));
        System.out.println(map);
    }

    @Test
    public void computeIfAbsent(){
        Map<String,String> map = Maps.newConcurrentMap();
        map.put("AA","BB");
        map.computeIfAbsent("AA",s -> "CC");
        System.out.println(map);
    }

    @Test
    public void computeIfPresent() {
        Map<String,String> map = Maps.newConcurrentMap();
        map.put("AA","BB");
        map. computeIfPresent("AA",(s, s2) -> s+s2) ;
        System.out.println(map);
    }
}
