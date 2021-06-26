package com.skin.demo.nio.chat;

import org.omg.CORBA.PUBLIC_MEMBER;

import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;

public class NioClientHandler implements Runnable {

    private Selector selector;

    public NioClientHandler(Selector selector) {
        this.selector = selector;

    }

    @Override
    public void run() {
        while (true) {
            try {
                int readyChannel = selector.select();
                if (readyChannel == 0) continue;

                Iterator<SelectionKey> selectionKeyIterator = selector.selectedKeys().iterator();
                while (selectionKeyIterator.hasNext()) {
                    //获得SelectionKey
                    SelectionKey selectionKey = (SelectionKey) selectionKeyIterator.next();

                    //移除SelectionKey
                    selectionKeyIterator.remove();

                    if (selectionKey.isReadable()) {
                        read(selectionKey, selector);
                    }

                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
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
            System.out.println("服务器端可读:::" + str);
        }
    }
}
