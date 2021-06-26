package com.skin.demo.initializeClass;

/**
 * @description:
 * @author: moshiqing
 * @time: 2020/4/17 10:50
 */
public class initialize {

    public static String staticField = "静态变量";

    public String field = "变量";

    public final String finalTemp = "final字段";

    public final static String finalStaticTemp = "静态final字段";

    // 静态初始化块
    static {
        System.out.println(staticField);
        System.out.println(finalStaticTemp);
        System.out.println("静态初始化块");
    }

    // 初始化块
    {
        System.out.println(finalTemp);
        System.out.println(field);
        System.out.println("初始化块");
    }

    // 构造器
    public initialize() {
        System.out.println("构造器");
    }

    public static void main(String[] args) {
        new initialize();
    }

}
