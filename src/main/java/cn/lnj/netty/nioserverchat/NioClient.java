package cn.lnj.netty.nioserverchat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NioClient {

    public static void main(String[] args) throws IOException {


        try {
            SocketChannel socketChannel = SocketChannel.open();
            socketChannel.configureBlocking(false);
            Selector selector = Selector.open();
            socketChannel.register(selector, SelectionKey.OP_CONNECT);
            socketChannel.connect(new InetSocketAddress("localhost", 8899));

            while (true) {

                selector.select();
                Set<SelectionKey> keySet = selector.selectedKeys();
                for (SelectionKey key : keySet) {
                    if (key.isConnectable()) {
                        SocketChannel client = (SocketChannel) key.channel();

                        if (client.isConnectionPending()) {
                            client.finishConnect();
                            ByteBuffer writeBuffer = ByteBuffer.allocate(1024);
                            writeBuffer.put((LocalDateTime.now() + " 连接成功.").getBytes());
                            writeBuffer.flip();
                            client.write(writeBuffer);

                            ExecutorService executorService = Executors.newSingleThreadExecutor(Executors.defaultThreadFactory());
                            executorService.submit(() -> {

                                while (true) {
                                    try {
                                        writeBuffer.clear();
                                        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                                        String readMessage = br.readLine();

                                        writeBuffer.put(readMessage.getBytes());
                                        writeBuffer.flip();
                                        client.write(writeBuffer);

                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                            });
                        }

                        client.register(selector, SelectionKey.OP_READ);

                    }else if(key.isReadable()) {
                        SocketChannel client = (SocketChannel) key.channel();
                        ByteBuffer serviceBuffer = ByteBuffer.allocate(1024);
                        serviceBuffer.clear();
                        int readNumbers = client.read(serviceBuffer);
                        if(readNumbers > 0) {
                            Charset charset = Charset.forName("utf-8");
                            String writeMessage = String.valueOf(charset.decode(serviceBuffer).array());
                            System.out.println(writeMessage);
                        }

                    }

                }


            }


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
