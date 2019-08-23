package cn.lnj.netty.nioserverchat;


import io.netty.buffer.ByteBuf;

import javax.swing.text.html.HTMLDocument;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.*;

public class NioServer {

    private static Map<String, SocketChannel> clientMap = new HashMap<>();

    public static void main(String[] args) throws IOException {

        //ServerSocketChannel 关注连接这个事件
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        ServerSocket serverSocket = serverSocketChannel.socket();
        serverSocket.bind(new InetSocketAddress(8899));

        Selector selector = Selector.open();
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        while (true) {
            try {
                selector.select();
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectionKeys.iterator();
                while (iterator.hasNext()) {
                    SelectionKey selectionKey = iterator.next();
                    final SocketChannel client;
                    if (selectionKey.isAcceptable()) {
                        //表示客户端已经建立连接
                        ServerSocketChannel server = (ServerSocketChannel) selectionKey.channel();
                        client = server.accept();
                        client.configureBlocking(false);
                        client.register(selector, SelectionKey.OP_READ);

                        String key = "【" + UUID.randomUUID().toString() + "】";
                        clientMap.put(key, client);

                        iterator.remove();
                    } else if (selectionKey.isReadable()) {
                        //当数据可读，且操作系统已经获取这些数据，isReadable 就会返回 true

                        SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
                        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                        byteBuffer.clear();
                        int read = socketChannel.read(byteBuffer);
                        if (read > 0) {

                            byteBuffer.flip();
                            Charset charset = Charset.forName("UTF-8");
                            String receiveMessage = String.valueOf(charset.decode(byteBuffer).array());
                            System.out.println(socketChannel + ":" + receiveMessage);

                            String senderKey = null;
                            for (Map.Entry<String, SocketChannel> entry : clientMap.entrySet()) {
                                if (socketChannel == entry.getValue()) {
                                    senderKey = entry.getKey();
                                    break;
                                }
                            }

                            for (Map.Entry<String, SocketChannel> entry : clientMap.entrySet()) {

                                SocketChannel value = entry.getValue();
                                ByteBuffer writeBuffer = ByteBuffer.allocate(1024);
                                writeBuffer.put((senderKey + ":" + receiveMessage).getBytes());
                                writeBuffer.flip();
                                value.write(writeBuffer);

                            }


                        }

                        iterator.remove();

                    }

                }


            } catch (Exception ex) {
                ex.printStackTrace();
            }


        }

    }

}
