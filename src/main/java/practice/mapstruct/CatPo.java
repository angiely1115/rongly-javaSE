package practice.mapstruct;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Map;

/**
 * @Author: lvrongzhuan
 * @Description:
 * @Date: 2019/3/8 15:00
 * @Version: 1.0
 * modified by:
 */
@Setter
@Getter
@ToString
public class CatPo implements Serializable {
    private static final long serialVersionUID = 1113504171346565693L;
    private String name;
    private int age;
    private String sex;
    private Map<String,String> stringMap;

}
