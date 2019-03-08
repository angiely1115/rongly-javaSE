package practice.ObjectStream;

import java.io.Serializable;

/**
 * @Author lvrongzhuan
 * @desc: 序列化
 * @createTime 2018/2/27 15:27
 * @Modified by:
 */
public class Person implements Serializable{
    private static final long serialVersionUID = -7841383292607988807L;
    private String name;
    private int age;
    //不被序列化
    private transient String address;
    /**
     * 静态属性也不能被序列化
     */
    public static String nikeName;


    public Person(String name, int age, String address) {
        this.name = name;
        this.age = age;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", address='" + address + '\'' +
                '}';
    }
}
