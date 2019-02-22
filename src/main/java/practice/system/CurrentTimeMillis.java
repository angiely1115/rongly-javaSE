package practice.system;

import java.time.LocalDateTime;

/**
 * @Author: lvrongzhuan
 * @Description:
 * @Date: 2018/12/24 23:47
 * @Version: 1.0
 * modified by:
 */
public class CurrentTimeMillis {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        for (long i = 0; i < Integer.MAX_VALUE; i++) {
          SystemClock.now();
        }
        long end = System.currentTimeMillis();
        System.out.println("SystemClock Time:" + (end - start) + "毫秒");

         start = System.currentTimeMillis();
        for (long i = 0; i < Integer.MAX_VALUE; i++) {
            //最慢 慢的你无法想象
            //LocalDateTime.now();
        }
         end = System.currentTimeMillis();
        System.out.println("localDateTime Time:" + (end - start) + "毫秒");
        long start2 = System.currentTimeMillis();
        for (long i = 0; i < Integer.MAX_VALUE; i++) {
            System.currentTimeMillis();
        }
        long end2 = System.currentTimeMillis();
        System.out.println("currentTimeMillis Time:" + (end2 - start2) + "毫秒");
    }

}
