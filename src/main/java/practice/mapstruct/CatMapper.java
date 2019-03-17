package practice.mapstruct;

import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

/**
 * @Author: lvrongzhuan
 * @Description:
 * @Date: 2019/3/8 16:02
 * @Version: 1.0
 * modified by:
 */
@Mapper
public interface CatMapper {

    CatMapper CAT_MAPPER = Mappers.getMapper(CatMapper.class);

    CatPo catVoToCatPo(CatVo catVo);

    CatVo catPoToCatVo(CatPo catPo);
}
