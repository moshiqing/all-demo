package com.skin.demo.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.util.CharsetUtil;

public class MyChannelInitializer extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        StudentPOJO.student student = StudentPOJO.student.newBuilder().setId(25).setName("你是谁").build();
        ctx.writeAndFlush(student);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("当前服务连接通道地址 service" + ctx.channel().remoteAddress());
//        ByteBuf byteBuf=(ByteBuf) msg;
//        System.out.println("当前客户端信息"+byteBuf.toString(CharsetUtil.UTF_8));
        //读取客户端数据
        StudentPOJO.student student = (StudentPOJO.student) msg;
        System.out.println("当前客户端信息" + student.getId() + "::" + student.getName());
        StudentPOJO.student student1 = StudentPOJO.student.newBuilder().setId(80).setName("我是服务端").build();
        ctx.writeAndFlush(student1);
    }


//    @Override
//    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
//        ctx.writeAndFlush(Unpooled.copiedBuffer("i konw you come here", CharsetUtil.UTF_8));
//    }
}
