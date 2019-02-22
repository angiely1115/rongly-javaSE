package practice.Introspector;

/**
 * @Author lvrongzhuan
 * @desc:
 * @createTime 2018/2/2810:46
 * @Modified by:
 */
public class User {
    private String name;
    private String address;
    private int age;

    public User() {
    }

    public User(String name, String address, int age) {
        this.name = name;
        this.address = address;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
