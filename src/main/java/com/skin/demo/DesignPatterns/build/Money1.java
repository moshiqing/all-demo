package com.skin.demo.DesignPatterns.build;

/**
 * @description:建造者模式
 * @author: moshiqing
 * @time: 2020/4/3 11:02
 */
public class Money1 {

    int num;
    int type;

    Money1(Builder builder) {
        num = builder.num;
        type = builder.type;
    }

    public static Money1.Builder Builder() {
        return new Money1.Builder();
    }

    /**
     * 建造者
     */
    public static class Builder {
        int num;
        int type;

        Builder() {
        }

        public Money1.Builder createNum(int num) {
            this.num = num;
            return this;
        }

        public Money1.Builder type(int type) {
            this.type = type;
            return this;
        }

        public Money1 build() {
//            System.out.println(" builder  this:::"+this);
            return new Money1(this);
        }

        @Override
        public String toString() {
            return "builder{" +
                    "num=" + num +
                    ", type=" + type +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "Money{" +
                "num=" + num +
                ", type=" + type +
                '}';
    }

    public static void main(String[] args) {
        System.out.println("----------------------------------------");
        Money1 build1 = Money1.Builder().createNum(80).type(50).build();
        System.out.println(build1.toString());
    }
}
