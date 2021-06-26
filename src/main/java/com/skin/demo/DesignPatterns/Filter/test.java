package com.skin.demo.DesignPatterns.Filter;

/**
 * @description:
 * @author: moshiqing
 * @time: 2020/4/10 16:00
 */
public class test {

    private static AbstractFilter getlogger() {
        AbstractFilter errorLogger = new ErrorLogger(1);
        AbstractFilter consoleLogger = new ConsoleLogger(2);
        AbstractFilter fileLogger = new FileLogger(3);

        errorLogger.setNextFilter(consoleLogger);
        consoleLogger.setNextFilter(fileLogger);

        return errorLogger;
    }

    public static void main(String[] args) {
        AbstractFilter getlogger = getlogger();

        getlogger.filter(1, "111111111111");
        getlogger.filter(2, "22222222222222");
        getlogger.filter(3, "33333333333333");

    }
}
