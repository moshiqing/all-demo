package com.skin.demo.DesignPatterns.TemplateMethod;

/**
 * @description:
 * @author: moshiqing
 * @time: 2020/3/25 9:38
 */
public class Plus extends AbstractCalculator {

    @Override
    public int calculate(int num1, int num2) {
        return num1 + num2;
    }

    public static void main(String[] args) {
        String exp = "8+8";
        AbstractCalculator cal = new Plus();
        int result = cal.calculate(exp, "\\+");
        System.out.println(result);
    }

}
