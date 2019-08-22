package cn.lnj.nio;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class NioServer {

    public static void main(String[] args) throws Exception {

        //用于建立连接
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        //配置成非阻塞的
        serverSocketChannel.configureBlocking(false);
        ServerSocket serverSocket = serverSocketChannel.socket();
        serverSocket.bind(new InetSocketAddress(8899));

        Selector selector = Selector.open();
        // 将channer注册到Selector上，监听accept事件
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        while (true) {
            // 返回所关注的事件的数量
            int numbers = selector.select();

            Set<SelectionKey> selectionKeys = selector.selectedKeys();

            selectionKeys.forEach(selectionKey -> {

                //关注数据的读取这个事件
                final SocketChannel client;

                //判断selectionKey的类型
                try {

                    if(selectionKey.isAcceptable()) {
                        //返回的是一个父类型，所以要强制转换成子类型
                        //注册的事件类型不同，当事件发生后，知道事件类型（OP_ACCEPT）
                        ServerSocketChannel server = (ServerSocketChannel) selectionKey.channel();
                        client = server.accept();
                        client.configureBlocking(false);
                        //关注的事件是OP_READ
                        client.register(selector, SelectionKey.OP_READ);

                    }


                } catch (Exception e) {
                    e.printStackTrace();
                }


            });


        }

    }

}
