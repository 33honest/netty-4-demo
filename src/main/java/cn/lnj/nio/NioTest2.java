package cn.lnj.nio;

import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class NioTest2 {

    public static void main(String[] args) throws Exception {

        FileOutputStream pos = new FileOutputStream("test2.txt");
        FileChannel fc = pos.getChannel();

        byte[] message = "Hello Nio World, study java technology .".getBytes();
        System.out.println(message.length);
        ByteBuffer buffer = ByteBuffer.allocate(message.length);
        System.out.println(buffer.limit());
        for (int i = 0; i < message.length; i++) {
            buffer.put(message[i]);

        }

        buffer.flip();
        fc.write(buffer);
        pos.close();

    }

}
