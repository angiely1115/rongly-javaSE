package practice.ClassLoadTest.myClassLoad;

/**
 * 自定义类加载器
 */

public class Parent2 {
    protected static String CLASS_NAME;
    protected static String CLASS_LOADER_NAME;
    protected String instanceID;


	// 1.先执行静态变量和静态代码块（只在类加载期间执行一次）
    static {
        CLASS_NAME = Parent2.class.getName();
        CLASS_LOADER_NAME = Parent2.class.getClassLoader().toString();
        System.out.println("Step a: " + CLASS_NAME + " is loaded by " + CLASS_LOADER_NAME);
    }

    // 2.然后执行变量和普通代码块（每次创建实例都会执行）
    {
        instanceID = this.toString();
        System.out.println("Step c: Parent instance is created: " + CLASS_LOADER_NAME + " -> " + instanceID);
    }

    public Parent2(String instanceID) {
        this.instanceID = instanceID;
    }

    // 3.然后执行构造方法
    public Parent2() {
        System.out.println("Step d: Parent instance：" + instanceID + ", constructor is invoked");
    }

    public void say() {
        System.out.println("Parent My first class loader...");
    }
}
