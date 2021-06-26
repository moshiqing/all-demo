package com.skin.demo.netty;

import lombok.Data;

@Data
public class message {
    public int length;
    public byte[] context;
}
