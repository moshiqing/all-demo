package com.skin.demo.DesignPatterns.build;

/**
 * @description:建造者模式
 * @author: moshiqing
 * @time: 2020/4/3 11:02
 */
public class Money {

    int num;

    Money() {
        this(new tools());
    }

    Money(tools tools) {
        System.out.println("触发 Money tools 构造函数");
        num = tools.num;
    }

    public tools newTools() {
        System.out.println(" Money  this:::" + this);
        return new tools(this);
    }

    /**
     * 建造者
     */
    public static final class tools {

        int num;

        tools() {
            num = 5;
        }

        public tools(Money money) {
            this.num = money.num;
        }

        public tools createNum(int num) {
            this.num = num;
            return this;
        }

        public Money build() {
            System.out.println(" tools  this:::" + this);
            return new Money(this);
        }

        @Override
        public String toString() {
            return "tools{" +
                    "num=" + num +
                    '}';
        }
    }


    @Override
    public String toString() {
        return "Money{" +
                "num=" + num +
                '}';
    }

    public static void main(String[] args) {
        Money build = new Money().newTools().createNum(50).build();
        System.out.println(build.toString());
        System.out.println("----------------------------------------");
        Money build1 = new Money.tools().createNum(80).build();
        System.out.println(build1.toString());
    }
}
