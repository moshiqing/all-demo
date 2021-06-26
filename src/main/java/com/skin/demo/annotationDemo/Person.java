package com.skin.demo.annotationDemo;

@Table("person")
public class Person {

    @Column("name")
    private String name;

    @Column("old")
    private String old;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOld() {
        return old;
    }

    public void setOld(String old) {
        this.old = old;
    }

    public String print(String str) {
        return "你来了" + str;
    }
}


