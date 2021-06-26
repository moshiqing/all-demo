package com.skin.demo.nio.chat;

import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Scanner;

public class NioClient {

    public void start() throws Exception {


        SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 8080));

        Selector selector = Selector.open();
        socketChannel.configureBlocking(false);
        socketChannel.register(selector, SelectionKey.OP_READ);
        new Thread(new NioClientHandler(selector)).start();

//        String str="发送信息给服务器端";
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String str = scanner.nextLine();
            if (str != null && str.length() > 0) {
                socketChannel.write(Charset.forName("UTF-8").encode(str));
            }
        }

    }

    public static void main(String[] args) throws Exception {
        NioClient nioClient = new NioClient();
        nioClient.start();
    }
}
