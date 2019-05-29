package cn.lnj.nio;

import java.nio.ByteBuffer;

/**
 * 两个缓冲区的位置，限制，标记值都是独立的。
 * 新的字节缓冲区，底层数据引用的都是同一块内存区域。
 */
public class NioTest4 {

    public static void main(String[] args) {


        ByteBuffer buffer = ByteBuffer.allocate(10);
        for (int i = 0; i < buffer.capacity(); i++) {
            buffer.put((byte) i);
        }

        buffer.position(2);
        buffer.limit(6);

        ByteBuffer sliceBuffer = buffer.slice();

        for (int i = 0; i < sliceBuffer.capacity(); i++) {
            byte b = sliceBuffer.get(i);
            b *= 2;
            sliceBuffer.put(i, b);
        }

        buffer.position(0);
        buffer.limit(buffer.capacity());

        while (buffer.hasRemaining()) {
            System.out.println(buffer.get());
        }

    }

}
