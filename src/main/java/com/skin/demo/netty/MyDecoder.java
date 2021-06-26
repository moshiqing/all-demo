package com.skin.demo.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;

import java.util.List;

public class MyDecoder extends ReplayingDecoder<Void> {


    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        System.out.println("调用自定义解码器");
        int length = in.readInt();
        byte[] context = new byte[length];
        in.readBytes(context);


        message m = new message();
        m.setLength(length);
        m.setContext(context);

        out.add(m);
    }
}
