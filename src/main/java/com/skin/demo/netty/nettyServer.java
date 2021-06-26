package com.skin.demo.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * 服务测试接收到自定义编码使用
 */
public class nettyServer {

    public void bind(int port) {
        NioEventLoopGroup boss = new NioEventLoopGroup();
        NioEventLoopGroup child = new NioEventLoopGroup();
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(boss, child);
            bootstrap.channel(NioServerSocketChannel.class);
            bootstrap.option(ChannelOption.SO_BACKLOG, 128);
            bootstrap.childHandler(new MyServerChannelInitializer1()
                    /**
                     * 注解部分代码为 protobuf作为数据传输工具
                     */
//                    new ChannelInitializer<SocketChannel>() {
//                @Override

//                protected void initChannel(SocketChannel socketChannel) throws Exception {
//                    ChannelPipeline pipeline = socketChannel.pipeline();

//                    pipeline.addLast(new ProtobufDecoder(StudentPOJO.student.getDefaultInstance()));
//                    pipeline.addLast(new ProtobufEncoder());
//                    pipeline.addLast(new MyChannelInitializer());
//
//                }
//            }
            );
            ChannelFuture channelFuture = bootstrap.bind(port).sync();

            System.out.println("启动成功!");

            channelFuture.channel().closeFuture().sync();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            boss.shutdownGracefully();
            child.shutdownGracefully();
        }

    }

    public static void main(String[] args) {
        nettyServer n = new nettyServer();
        n.bind(8956);
    }
}
