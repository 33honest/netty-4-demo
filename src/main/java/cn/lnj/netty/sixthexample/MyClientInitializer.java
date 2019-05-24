package cn.lnj.netty.sixthexample;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;


public class MyClientInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline cp = ch.pipeline();
        cp.addLast(new ProtobufVarint32FrameDecoder());
        cp.addLast(new ProtobufDecoder(DateInfo.Student.getDefaultInstance()));
        cp.addLast(new ProtobufVarint32LengthFieldPrepender());
        cp.addLast(new ProtobufEncoder());

        cp.addLast(new MyClientHandler());
    }

}
