package cn.lnj.nio;

import java.io.File;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;

public class MappedByteBufferTest {

    public static void main(String[] args) throws Exception {

        String inputFile = "NioTest_in.txt";
        String outputFile = "NioTest_out.txt";

        RandomAccessFile inRandomAccessFile = new RandomAccessFile(inputFile,"r");
        RandomAccessFile outRandomAccessFile = new RandomAccessFile(outputFile,"rw");

        FileChannel inFileChannel = inRandomAccessFile.getChannel();
        FileChannel outFileChannel = outRandomAccessFile.getChannel();

        long fileLeng = new File(inputFile).length();

        MappedByteBuffer inputData = inFileChannel.map(FileChannel.MapMode.READ_ONLY, 0, fileLeng);

        Charset charset = Charset.forName("utf-8");
        CharsetDecoder charsetDecoder = charset.newDecoder();
        CharsetEncoder charsetEncoder = charset.newEncoder();

        CharBuffer charBuffer = charsetDecoder.decode(inputData);
        ByteBuffer outputData = charsetEncoder.encode(charBuffer);

        outFileChannel.write(outputData);

        inRandomAccessFile.close();
        outFileChannel.close();

    }

}
