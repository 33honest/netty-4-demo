package cn.lnj.netty.thirdexample;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

public class MyChatClient {
	public static void main(String[] args) throws Exception {

		EventLoopGroup workGroup = new NioEventLoopGroup();

		try {
			Bootstrap bootstrap = new Bootstrap();
			bootstrap.group(workGroup).channel(NioSocketChannel.class).handler(new MyChatClientInitializer());
			ChannelFuture channelFuture = bootstrap.connect("localhost", 8899).sync();
			Channel channel = channelFuture.channel();
			
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			while(true) {
				String readLine = br.readLine();
				channel.writeAndFlush(readLine + "\r\n");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			workGroup.shutdownGracefully();
		}
		

	}
}
