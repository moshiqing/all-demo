package com.skin.demo.netty;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.nio.charset.Charset;

public class myclientHandler extends SimpleChannelInboundHandler<message> {

    private int count;

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        for (int i = 0; i < 10; i++) {
//            ByteBuf byteBuf = Unpooled.copiedBuffer("hello,server " + i, CharsetUtil.UTF_8);
            String str = "------客户端发送的信息" + i + "--------";
            byte[] context = str.getBytes(Charset.forName("utf-8"));
            int length = str.getBytes(Charset.forName("utf-8")).length;

            message m = new message();
            m.setContext(context);
            m.setLength(length);
            ChannelFuture channelFuture = ctx.writeAndFlush(m);
            boolean success = channelFuture.isSuccess();
            if (success) {
                System.out.println("发送成功");
            } else {
                System.out.println("发送失败");
            }
        }
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, message msg) {
        int length = msg.getLength();
        byte[] context = msg.getContext();
        String s = new String(context, Charset.forName("utf-8"));
        System.out.println("客户端接收到服务器给的回应了长度为" + length + "内容为" + s);
        System.out.println("这是第" + count++ + "接收到");
    }
}
