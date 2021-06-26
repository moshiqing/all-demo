package com.skin.demo.AbstractAndInteface;

import lombok.Data;

@Data
public class animal {

    private int c = 5;

    private int d = 6;

    void run() {
        System.out.println("动物都会跑");
    }

}
