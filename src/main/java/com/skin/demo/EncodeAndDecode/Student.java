package com.skin.demo.EncodeAndDecode;

import java.io.Serializable;

/**
 * @description:
 * @author: moshiqing
 * @time: 2020/2/27 11:52
 */
public class Student {

    public int id;
    public String name;
    public boolean sex;
    public String cellphone;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }
}
