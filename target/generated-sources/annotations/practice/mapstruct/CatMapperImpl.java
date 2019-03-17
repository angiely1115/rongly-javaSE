package practice.mapstruct;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2019-03-17T23:03:43+0800",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 1.8.0_201 (Oracle Corporation)"
)
public class CatMapperImpl implements CatMapper {

    @Override
    public CatPo catVoToCatPo(CatVo catVo) {
        if ( catVo == null ) {
            return null;
        }

        CatPo catPo = new CatPo();

        catPo.setName( catVo.getName() );
        catPo.setAge( catVo.getAge() );
        catPo.setSex( catVo.getSex() );
        Map<String, String> map = catVo.getStringMap();
        if ( map != null ) {
            catPo.setStringMap( new HashMap<String, String>( map ) );
        }

        return catPo;
    }

    @Override
    public CatVo catPoToCatVo(CatPo catPo) {
        if ( catPo == null ) {
            return null;
        }

        CatVo catVo = new CatVo();

        catVo.setName( catPo.getName() );
        catVo.setAge( catPo.getAge() );
        catVo.setSex( catPo.getSex() );
        Map<String, String> map = catPo.getStringMap();
        if ( map != null ) {
            catVo.setStringMap( new HashMap<String, String>( map ) );
        }

        return catVo;
    }
}
