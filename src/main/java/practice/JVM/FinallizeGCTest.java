package practice.JVM;

import jdk.nashorn.api.scripting.ScriptUtils;

import java.util.concurrent.TimeUnit;

/**
 * @Author: lvrongzhuan
 * @Description: 只会被调用一次
 * @Date: 2018/10/15 17:24
 * @Version: 1.0
 * modified by:
 */
public class FinallizeGCTest {

    private static FinallizeGCTest finallizeGCTest = null;

    /**
     * 只会执行一次
     * @throws Throwable
     */
    @Override
    protected void finalize() throws Throwable {
        finallizeGCTest = this;
        super.finalize();
        System.out.println("执行finalize 方法");
    }

    public static void main(String[] args) throws InterruptedException {
        finallizeGCTest = new FinallizeGCTest();
        finallizeGCTest = null;
        System.gc();
        //因为finalize优先级低 让线程休眠5s
        TimeUnit.SECONDS.sleep(5);
        if(finallizeGCTest!=null){
            System.out.println("hihi 我拯救了自己");
        }else{
            System.out.println("喔霍,拯救不了了");
        }
        finallizeGCTest = null;
        System.gc();
        //因为finalize优先级低 让线程休眠5s
        TimeUnit.SECONDS.sleep(5);
        if(finallizeGCTest!=null){
            System.out.println("hihi 我拯救了自己");
        }else{
            System.out.println("喔霍,拯救不了了");
        }
    }
}
