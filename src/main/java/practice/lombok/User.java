package practice.lombok;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
//通过静态方法构造对象实例 会生成私有的构造函数
@RequiredArgsConstructor(staticName = "newUserByNameAndAge")
public class User {
    @NonNull
    private String name;
    private String address;
    private Integer age;
}