package cn.lnj.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class NioTest3 {

    public static void main(String[] args) throws Exception {

        FileInputStream fis = new FileInputStream("Snipaste_2018-12-12_21-22-31.png");
        FileOutputStream fos = new FileOutputStream("newimg.png");

        FileChannel isChannel = fis.getChannel();
        FileChannel osChannel = fos.getChannel();

        ByteBuffer buffer = ByteBuffer.allocate(128);

        while(true) {
            buffer.clear();

            int read = isChannel.read(buffer);
            if(-1 == read) {
                break;
            }
            buffer.flip();
            osChannel.write(buffer);

        }

        fis.close();
        fos.close();


    }

}
