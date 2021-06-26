package com.skin.demo.nio.chat;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Set;

public class NioServer {

    /**
     * 启动服务器端
     */
    public void start() throws Exception {
        Selector selector = Selector.open();
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress(8080));
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        System.out.println("服务器启动成功");

        while (true) {
            int readyChannel = selector.select();
            if (readyChannel == 0) continue;

            Iterator<SelectionKey> selectionKeyIterator = selector.selectedKeys().iterator();

            while (selectionKeyIterator.hasNext()) {
                //获得SelectionKey
                SelectionKey selectionKey = selectionKeyIterator.next();

                //移除SelectionKey
                selectionKeyIterator.remove();

                //判断事件处理
                if (selectionKey.isAcceptable()) {
                    accept(serverSocketChannel, selector);
                }

                if (selectionKey.isReadable()) {
                    read(selectionKey, selector);
                }

            }
        }

    }

    public void accept(ServerSocketChannel serverSocketChannel, Selector selector) throws Exception {
        SocketChannel socketChannel = serverSocketChannel.accept();
        socketChannel.configureBlocking(false);
        socketChannel.register(selector, SelectionKey.OP_READ);
        socketChannel.write(Charset.forName("UTF-8").encode("连接上了服务器"));
    }

    public void read(SelectionKey selectionKey, Selector selector) throws Exception {
        SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        String str = "";
        while (socketChannel.read(byteBuffer) > 0) {
            byteBuffer.flip();
            str += Charset.forName("UTF-8").decode(byteBuffer);
        }
        socketChannel.register(selector, SelectionKey.OP_READ);
        if (str.length() > 0) {
            //广播
            System.out.println(":::" + str);
            broad(selector, socketChannel, str);

        }
    }

    public void broad(Selector selector, SocketChannel socketChannel, String str) {

        Set<SelectionKey> selectionKeySet = selector.keys();

        selectionKeySet.forEach(selectionKey -> {

            Channel channel = selectionKey.channel();
            if (channel instanceof SocketChannel
//                    && channel!=socketChannel
            ) {
                try {
                    System.out.println("准备发送信息");
                    ((SocketChannel) channel).write(Charset.forName("UTF-8").encode(str));
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
    }

    public static void main(String[] args) throws Exception {
        NioServer nioServer = new NioServer();
        nioServer.start();
    }
}
