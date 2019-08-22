package cn.lnj.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class NioTest0 {

    public static void main(String[] args) {

        int[] ports = new int[5];
        ports[0] = 8080;
        ports[1] = 8081;
        ports[2] = 8082;
        ports[3] = 8083;
        ports[4] = 8084;

        try {
            Selector selector = Selector.open();

            for (int i = 0; i < ports.length; i++) {
                ServerSocketChannel ssc = ServerSocketChannel.open();
                ssc.configureBlocking(false);
                ServerSocket serverSocket = ssc.socket();
                InetSocketAddress socketAddress = new InetSocketAddress(ports[i]);
                serverSocket.bind(socketAddress);

                ssc.register(selector, SelectionKey.OP_ACCEPT);
            }

            while (true) {

                int numbers = selector.select();
                System.out.println("numbers:" + numbers);
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> iter = selectionKeys.iterator();

                while (iter.hasNext()) {

                    SelectionKey selectionKey = iter.next();
                    if (selectionKey.isAcceptable()) {
                        ServerSocketChannel ssc = (ServerSocketChannel) selectionKey.channel();
                        SocketChannel socketChannel = ssc.accept();
                        socketChannel.configureBlocking(false);
                        socketChannel.register(selector, SelectionKey.OP_READ);
                        iter.remove();
                        System.out.println("获取客户端连接：" + socketChannel.getRemoteAddress());

                    } else if (selectionKey.isReadable()) {

                        SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
                        int byteRead = 0;
                        while (true) {
                            ByteBuffer buffer = ByteBuffer.allocate(512);
                            buffer.clear();
                            int read = socketChannel.read(buffer);
                            if (read <= 0) {
                                break;
                            }
                            buffer.flip();
                            socketChannel.write(buffer);

                            byteRead += read;
                            iter.remove();

                        }
                        System.out.println("读取：" + byteRead + "，来自：" + socketChannel);

                    }

                }
            }



        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
