package cn.lnj.nio;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Arrays;

public class NioTest5 {

    public static void main(String[] args) throws Exception {

        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        InetSocketAddress address = new InetSocketAddress(8899);
        serverSocketChannel.socket().bind(address);

        int messageLength = 2 + 3 + 4;
        ByteBuffer[] buffer = new ByteBuffer[3];
        buffer[0] = ByteBuffer.allocate(2);
        buffer[1] = ByteBuffer.allocate(3);
        buffer[2] = ByteBuffer.allocate(4);

        SocketChannel channel = serverSocketChannel.accept();


        while(true) {
            long byteRead = 0;
            while(byteRead < messageLength) {
                long r = channel.read(buffer);
                byteRead += r;
                System.out.println("bytesRead : " + byteRead);

                Arrays.asList(buffer).stream().
                        map(buf -> "position : " + buf.position() + ", limit : " + buf.limit()).
                        forEach(System.out::println);
            }

            Arrays.asList(buffer).forEach(buf -> {
                buf.flip();
            });

            long bytesWrite = 0;
            while (bytesWrite < messageLength) {

                long w = channel.write(buffer);
                bytesWrite += w;
            }

            Arrays.asList(buffer).forEach(buf -> {
                buf.clear();
            });

            System.out.println("byteRead : " + byteRead + ", byteWrite : " + bytesWrite +
                    ", messageLength : " + messageLength);

        }

    }

}
