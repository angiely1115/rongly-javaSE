package practice.objectReference;

/**
 * @Author: lvrongzhuan
 * @Description: 简单的对象
 * @Date: 2018/8/20 17:37
 * @Version: 1.0
 * modified by:
 */
public class User {

    private String name;

    private String address;

    public User(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public User() {
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
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

    public void paramUser(User user){
        User user1 = new User("白素贞","四川峨眉");
        user.setAddress("白素贞");
    }

    public static void main(String[] args) {
        User user = new User();
        user.paramUser(user);
        System.out.println(user);
    }
}
