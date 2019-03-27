package com.lv.test;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

/**
 * @Author: lvrongzhuan
 * @Description: 单元测试demo
 * @Date: 2019/3/23 19:34
 * @Version: 1.0
 * modified by:
 */
@DisplayName(value = "单元测试demo")
public class TestDemo {

    /**
     * 必须的静态方法 只执行一次
     */
    @BeforeAll
    public static void init() {
        System.out.println("整个测试类初始化调用");
    }

    /**
     * 必须是静态方法 只执行一次
     */
    @AfterAll
    public static void clean() {
        System.out.println("整个测试完成后调用");
    }

    @BeforeEach
    public void beforeMethod() {
        System.out.println("每个测试方法执行前调用");
    }

    @AfterEach
    public void afterMethod() {
        System.out.println("每个测试方法执行后调用");
    }

    @Test
    @DisplayName("第一个简单的测试方法")
    public void test01() {
        System.out.println("第一个简单的测试方法");
    }

    @Test
    @DisplayName("第二个简单的测试方法")
    public void test02() {
        System.out.println("第二个简单的测试方法");
    }

    /**
     * 还配置maven插件
     */
    @Test
    @DisplayName("第三带tag标签个简单的测试方法")
    @Tag("test03 有点问题")
    public void test03() {
        System.out.println("第三个带tag标签简单的测试方法");
    }

    @DisplayName("参数自动化测试")
    @ParameterizedTest
    @CsvSource(value = {"1,2,5","1,2,3","1,白素贞,3"})
    public void testParmas(int a,String b,int c) {
        parmasTest(a,b,c);
    }

    private void parmasTest(int a,String b,int c) {
        System.out.println(a==1&&"白素贞".equals(b)||c==3);
    }


}
