package practice.objectReference;

import org.junit.jupiter.api.Test;

import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

/**
 * @Author: lvrongzhuan
 * @Description 强引用 软引用 弱引用 虚引用
 * @Date: 2018/11/16 21:11
 * @Version: 1.0
 * modified by:
 */
public class ReferenceDemo {

    Object object = new Object();
    /**
     *  测强引用
     */
    @Test
    public void strong(){
        Object obj = object;
        object = null;
        System.gc();
        //gc 过后值还存在 只有当 ReferenceDemo 销毁的时候才释放
        System.out.println(obj);
    }

    //强引用
    public void strong(ReferenceDemo referenceDemo){
        Object obj = object;
        object = null;
        referenceDemo = null;
        System.gc();
        //gc 过后值还存在 只有当 ReferenceDemo 销毁的时候才释放
        System.out.println(obj);
    }

    /**
     * 软引用 在抛出oom之前回收 意思在内存空间不足的情况下回收
     */
    @Test
    public void testSoft(){
        Object o = new Object();
        SoftReference softReference = new SoftReference(o);
//        o = null;
        o = null;
//        softReference = null;
        Byte[] bytes = new Byte[6*1024*1024];
        System.gc();
        System.out.println(softReference.get());
    }

    /**
     *不会在内存不足的情况被回收，只要在gc线程运行时候如果当前对象没有被引用就会回收 弱引用不会回收String类型
     * 也就是说不会回收常量池的东西
     */
    @Test
    public void testWeak(){
        Object obj = object;
        WeakReference weakReference = new WeakReference(obj);
//        o=null;
        object = null;
//        obj = null;
        System.gc();
        System.out.println(weakReference.get());
        System.out.println(obj);
    }

}
