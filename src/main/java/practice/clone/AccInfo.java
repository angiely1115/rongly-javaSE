package practice.clone;

import com.google.common.collect.Lists;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Author: lvrongzhuan
 * @Description:
 * @Date: 2019/8/12 21:42
 * @Version: 1.0
 * modified by:
 */
@Data
public class AccInfo implements Cloneable{
    private String accName;

    @Override
    protected AccInfo clone() throws CloneNotSupportedException {
        AccInfo accInfo = (AccInfo) super.clone();
        accInfo.strings = Lists.newArrayList();
        return accInfo;
    }

    private BigDecimal amount;

    private int age;
    private List<String> strings ;

    public static void main(String[] args) throws CloneNotSupportedException {
        AccInfo accInfo = new AccInfo();
        accInfo.setAccName("穷逼");
        accInfo.setAmount(BigDecimal.valueOf(100000L));
        accInfo.setAge(30);
        accInfo.setStrings(Lists.newArrayList("1","2"));
        System.out.println("clone 前 accinfo:"+accInfo);
       AccInfo accInfo1 = (AccInfo) accInfo.clone();
        System.out.println(accInfo.getAccName()==accInfo1.getAccName());
        String accName = accInfo1.getAccName();
        accInfo1.setAccName(accName.concat("屌丝"));
        accInfo1.setAmount(BigDecimal.ZERO);
       List<String> stringList =  accInfo1.getStrings();

        stringList.add("3");
        accInfo1.setStrings(stringList);
        accInfo1.setAge(20);
//        accInfo.setStrings(Lists.newArrayList("2","2"));
        System.out.println(accInfo.getAccName()==accInfo1.getAccName());
        System.out.println("clone 后 accinfo:"+accInfo);
        System.out.println("clone 后 accInfo1:"+accInfo1);

    }
}
