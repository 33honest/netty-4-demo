package cn.lnj.nio;

import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**


 java.io中拥有3个核心概念：Selector，Channel，Buffer。在java.nio中，我们是面向块（block）或缓冲区编程。Buffer本身就是一块内存，底层实现上，它实际就是一个数组。数据的读，写都是通过Buffer来实现的。

 状态翻转，读写切换
 buffer.flip();

 Channel指的是可以向其写入数据或从中读数据的对象，它类似于java.io中的Strem。
 所有数据的读写都是通过Buffer来进行的，永远不支出现直接向Channel写入数据的情况，或是直接
 从Channel读取数据的情况。
 与Stream不同的是，Channel是双向的，一个流只可能是InputSteram或OutputStream，Channel打开后则可以进行读取，写入或是读写。


 */

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