package com.skin.demo.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

public class clientHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(ctx.channel().id().asShortText() + "当前通道准备完毕");
//        ctx.writeAndFlush(Unpooled.copiedBuffer("我是客户端，我向服务器法奥是那个了信息", CharsetUtil.UTF_8));
        StudentPOJO.student student = StudentPOJO.student.newBuilder().setId(25).setName("你是谁").build();
        ctx.writeAndFlush(student);
        System.out.println("发送完成" + student.toString());
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//        ByteBuf byteBuf=(ByteBuf) msg;
        //        System.out.println("收取到当前的通道"+ctx.channel().id().asShortText()+"信息"+byteBuf.toString(CharsetUtil.UTF_8));

        StudentPOJO.student student = (StudentPOJO.student) msg;
        System.out.println("当前客户端信息" + student.getId() + "::" + student.getName());

    }
}
