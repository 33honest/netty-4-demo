package cn.lnj.netty.fourthexample;

import java.util.concurrent.TimeUnit;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.timeout.IdleStateHandler;

public class MyServerInitializer extends ChannelInitializer<SocketChannel> {

	@Override
	protected void initChannel(SocketChannel ch) throws Exception {
		ChannelPipeline pipeline = ch.pipeline();
		pipeline.addLast("idleStateHandler", new IdleStateHandler(5, 7, 10, TimeUnit.SECONDS));
		pipeline.addLast("myServerHandler", new MyServerHandler());
	}

}
