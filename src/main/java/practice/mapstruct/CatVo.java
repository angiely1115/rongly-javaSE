package practice.mapstruct;

import lombok.Data;

import java.io.Serializable;
import java.util.Map;

/**
 * @Author: lvrongzhuan
 * @Description:
 * @Date: 2019/3/8 15:00
 * @Version: 1.0
 * modified by:
 */
@Data
public class CatVo implements Serializable {
    private static final long serialVersionUID = 1113504171346565643L;
    private String name;
    private int age;
    private String sex;
    private Map<String,String> stringMap;

}
