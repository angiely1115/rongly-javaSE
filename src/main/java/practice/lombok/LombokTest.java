package practice.lombok;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;
import org.junit.jupiter.api.Test;

/**
 * @Author: lvrongzhuan
 * @Description: lombok 相关测试
 * @Date: 2019/3/17 22:37
 * @Version: 1.0
 * modified by:
 */
public class LombokTest {

    /**
     * 测试链式方式
     */
    @Test
    public void testLombokChain() {
   /*     User user = new User();
        user.setAddress("湖南长沙").setAge(100).setName("岳麓");
        System.out.println(user);
        */
        User user1 = User.newUserByNameAndAge("大唐");
        System.out.println("user1:"+user1);

    }


}
