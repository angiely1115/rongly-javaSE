package practice.lombok;

import java.text.SimpleDateFormat;
import java.util.Date;

import lombok.Data;
import lombok.experimental.ExtensionMethod;

@Data
@ExtensionMethod({java.util.Arrays.class, Extensions.class,SimpleDateFormat.class})
public class ExtensionMethodDemo {
    private void test() {
        Date date=new Date();
        String d=date.toLocaleString();
        System.out.println(d);
    }
    public static void main(String[] args) {
        new ExtensionMethodDemo().test();
    }

}