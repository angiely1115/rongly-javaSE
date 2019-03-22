package practice.date;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @Author: lvrongzhuan
 * @Description: 日期测试类
 * @Date: 2019/3/21 10:47
 * @Version: 1.0
 * modified by:
 */
public class DateTest {
    @Test
    public void test1() {
        LocalDate localDate = LocalDate.now();
        LocalDate beforLocalDate = localDate.plusDays(-3);
        System.out.println(localDate.toString());
        System.out.println(beforLocalDate.toString());
        System.out.println(LocalDateTime.parse("2019-02-20 09:00:13", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
    }
}
