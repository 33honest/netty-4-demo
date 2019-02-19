package cn.lnj.netty.secondexample;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;

public class MyClientInitializer extends ChannelInitializer<SocketChannel> {
	@Override
	protected void initChannel(SocketChannel ch) throws Exception {
		// TODO Auto-generated method stub
		ChannelPipeline pipeline = ch.pipeline();
		pipeline.addLast("lengthFieldBasedFrameDecoder", new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 0, 4, 0, 4));
		pipeline.addLast("lengthFieldPrepender", new LengthFieldPrepender(4));
		pipeline.addLast("stringDecoder", new StringDecoder(CharsetUtil.UTF_8));
		pipeline.addLast("stringEncoder", new StringEncoder(CharsetUtil.UTF_8));
		pipeline.addLast("myClientHandler", new MyClientHandler());
	}
}
