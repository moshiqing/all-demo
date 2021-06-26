package com.skin.demo.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.FullHttpMessage;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.util.concurrent.EventExecutorGroup;

import java.nio.charset.Charset;

public class myServerHandler extends SimpleChannelInboundHandler<message> {

    private int count;

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, message msg) throws Exception {

//        byte[] buffer=new byte[msg.readableBytes()];
//        msg.readBytes(buffer);
//
//        String str = new String(buffer, Charset.forName("utf-8"));
//        System.out.println("服务器接收到的字符串"+str);
//        System.out.println("这是第"+ count++ +"接收到");


        int length = msg.getLength();
        byte[] context = msg.getContext();
        String s = new String(context, Charset.forName("utf-8"));
        System.out.println("服务器接收到信息，长度为" + length + "内容为" + s);
        System.out.println("这是第" + count++ + "接收到");
    }
}
