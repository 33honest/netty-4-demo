package cn.lnj.netty.sixthexample;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class MyServerHandler extends SimpleChannelInboundHandler<DateInfo.Student> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, DateInfo.Student msg) throws Exception {

        System.out.println(msg.getName());
        System.out.println(msg.getAddress());
        System.out.println(msg.getAge());
    }
}
