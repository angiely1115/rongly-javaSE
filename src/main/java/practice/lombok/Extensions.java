package practice.lombok;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Extensions {
    public static String format(Date date){
        SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return df.format(date);
    }
}