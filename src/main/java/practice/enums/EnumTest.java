package practice.enums;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

/**
 * @Author: lvrongzhuan
 * @Description:
 * @Date: 2019/3/12 15:05
 * @Version: 1.0
 * modified by:
 */
public class EnumTest {
    @Test
    public void testEnumEquals() {
        testEnum(FilterPurposeEnum.ASSET_FILTER);
    }
    private void testEnum(FilterPurposeEnum filterPurposeEnum){
        System.out.println(filterPurposeEnum.equals(FilterPurposeEnum.ASSET_FILTER));
        new BigDecimal("");
    }
}
