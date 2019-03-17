package practice.mapstruct;

import com.google.common.collect.Maps;
import org.junit.jupiter.api.Test;

import java.util.Map;

/**
 * @Author: lvrongzhuan
 * @Description: 实体之间转换测试
 * @Date: 2019/3/8 16:05
 * @Version: 1.0
 * modified by:
 */
public class MapStructTest {

    @Test
    public void testMapStruct1(){
        CatVo catVo = new CatVo();
        catVo.setAge(11);
        catVo.setName("发财猫");
        catVo.setSex("女");
        Map<String,String> stringMap = Maps.newHashMap();
        stringMap.put("mapstruct","map");
        catVo.setStringMap(stringMap);
        CatPo catPo = CatMapper.CAT_MAPPER.catVoToCatPo(catVo);
        System.out.println(catPo);
    }
}
