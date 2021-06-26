package com.skin.demo.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class MyEncoder extends MessageToByteEncoder<message> {

    @Override
    protected void encode(ChannelHandlerContext ctx, message msg, ByteBuf out) throws Exception {
        System.out.println("自定义编码器被调用");
        int lenth = msg.getLength();
        byte[] context = msg.getContext();
        out.writeInt(lenth);
        out.writeBytes(context);
    }
}
