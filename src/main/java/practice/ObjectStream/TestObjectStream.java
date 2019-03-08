package practice.ObjectStream;

import org.junit.jupiter.api.Test;

import java.io.*;

/**
 * @Author lvrongzhuan
 * @desc:
 * @createTime 2018/2/2715:34
 * @Modified by:
 */
public class TestObjectStream {

    @Test
    public void test1() throws Exception {
        OutputStream outputStream = new FileOutputStream("a.txt");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
        Person person = new Person("lv",66,"永州城");
        Person.nikeName = "小白";
        objectOutputStream.writeObject(person);
        outputStream.close();
        objectOutputStream.close();
        InputStream inputStream = new FileInputStream("a.txt");
        ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
        System.out.println(objectInputStream.readObject());
        objectInputStream.close();
    }
    @Test
    public void TestRead() throws Exception {
        InputStream inputStream = new FileInputStream("a.txt");
        ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
        System.out.println(objectInputStream.readObject());
        inputStream.close();
        objectInputStream.close();
    }
}
