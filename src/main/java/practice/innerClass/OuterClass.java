package practice.innerClass;


/**
 * @Author lvrongzhuan
 * @desc: 内部类
 * @createTime 2018/2/25 20:59
 * @Modified by:
 */
public class OuterClass {//外部类
    public String name = "外部类名称";

    private static String name2 = "外部类静态变量";
    private int age;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public OuterClass() {
    }

    public OuterClass(String name, int age) {
        this.name = name;
        this.age = age;
    }
    public void showOuter(){
        System.out.println("外部类show");
    }

    public class InnerClass{//内部类
       public String innerName="第一层内部类名称";
       private Test1 test1;
       private int innerAge=68;

        public int getInnerAge() {
            return innerAge;
        }

        public void setInnerAge(int innerAge) {
            this.innerAge = innerAge;
        }

        public InnerClass() {
        }

        public InnerClass(String innerName, int innerAge) {
            this.innerName = innerName;
            this.innerAge = innerAge;
        }

        private void showInner(){
            System.out.println("内部类show");
            showOuter();
            System.out.println(name);
            age = 70;
        }

        public OuterClass getOuterClass(){
            return OuterClass.this;
        }
        class InnerClass2{//第二层内部类
            private String innerName2;
            public String  city;

            public void showInner2(){
                System.out.println("第二层内部类");
                System.out.println(name);
                System.out.println(innerName);

            }
        }
    }

    static class staticInnerClass{//静态内部类
        public String staticName="静态内部类名称";
        public static void staticshow(){
            System.out.println("静态内部类");
            System.out.println(name2);
        }

    }

    public void showInner(){
        System.out.println("外部类使用内部类");
        new InnerClass().showInner();
        staticInnerClass.staticshow();
    }

    public InnerClass getInnerClass(){
       return new InnerClass();
    }

    public InnerClass.InnerClass2 getInnerClass2(){
        return new InnerClass().new InnerClass2();
    }
    public static void main(String[] args) {
        OuterClass outerClass = new OuterClass();
        InnerClass innerClass = outerClass.getInnerClass();
        innerClass.showInner();
        System.out.println(innerClass.innerAge);
        System.out.println("外部类age:"+outerClass.age);
        outerClass.showInner();
        outerClass.getInnerClass2().showInner2();
    }
}
