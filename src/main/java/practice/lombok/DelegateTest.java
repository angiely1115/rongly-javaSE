package practice.lombok;

import lombok.experimental.Delegate;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @Author: lvrongzhuan
 * @Description:
 * @Date: 2019/3/17 23:02
 * @Version: 1.0
 * modified by:
 */
public class DelegateTest {
    private interface SimpleCollection {
        boolean add(String item);
        boolean remove(Object item);
    }

    @Delegate(types = SimpleCollection.class)
    private final List<String> collection = new ArrayList<String>();

    public static void main(String[] args) {
        DelegateTest user=new DelegateTest();
        user.add("item1");//实际上加到collection中去了
    }
}
