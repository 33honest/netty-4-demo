package cn.lnj.netty.secondexample;

import java.time.LocalDateTime;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class MyClientHandler extends SimpleChannelInboundHandler<String> {

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
		// TODO Auto-generated method stub
		System.out.println(ctx.channel().remoteAddress());
		System.out.println("client output : " + msg);
		ctx.writeAndFlush("from client : " + LocalDateTime.now());
	}
	
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		// TODO Auto-generated method stub
		ctx.writeAndFlush("来自客户端的问候！");
		super.channelActive(ctx);
	}

}
