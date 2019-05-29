package cn.lnj.nio;

import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class NioTest1 {

    public static void main(String[] args) throws Exception{

        FileInputStream fis = new FileInputStream("test2.txt");
        FileChannel fc = fis.getChannel();

        ByteBuffer buffer = ByteBuffer.allocate(512);
        fc.read(buffer);

        buffer.flip();
        while (buffer.remaining() > 0) {
            byte c = buffer.get();
            System.out.println("Charactor:" + (char) c);
        }

    }

}