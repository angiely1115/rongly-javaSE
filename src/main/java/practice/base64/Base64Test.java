package practice.base64;

import java.util.Base64;

/**
 * @Author: lvrongzhuan
 * @Description: base64 编解码 0-63的
 * @Date: 2019/3/22 17:40
 * @Version: 1.0
 * modified by:
 */
public class Base64Test {
    public static void main(String[] args) {
        String string= "hello world";
        //编码器
        Base64.Encoder encoder = Base64.getEncoder();
        byte[] bytes = encoder.encode(string.getBytes());
        System.out.println("编码之后:"+new String(bytes));
        //解码器
        Base64.Decoder decoder = Base64.getDecoder();
        bytes = decoder.decode(bytes);
        System.out.println("解码之后:"+new String(bytes));

    }
}
