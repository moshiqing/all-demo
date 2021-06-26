package com.skin.demo.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * 客户端测试自定义编码
 */
public class clientServer {

    public static void main(String[] args) throws Exception {
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(eventLoopGroup)
                .channel(NioSocketChannel.class)
                .handler(new MyClientChannelInitializer1()
//                        new ChannelInitializer<SocketChannel>() {
//                    @Override
//                    protected void initChannel(SocketChannel socketChannel) throws Exception {
//
//                       ChannelPipeline pipeline= socketChannel.pipeline();
//                        pipeline.addLast(new ProtobufDecoder(StudentPOJO.student.getDefaultInstance()));
//                        pipeline.addLast(new ProtobufEncoder());
//                        pipeline.addLast(new clientHandler());
//
//                    }
//                }
                );

        ChannelFuture channelFuture = bootstrap.connect("127.0.0.1", 8956).sync();
        channelFuture.channel().closeFuture().sync();
        System.out.println("客户端启动成功");

        eventLoopGroup.shutdownGracefully();
    }
}
