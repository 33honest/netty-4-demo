package cn.lnj.netty.sixthexample;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class MyClientHandler extends SimpleChannelInboundHandler<DateInfo.Student> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, DateInfo.Student msg) throws Exception {

    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        DateInfo.Student student = DateInfo.Student.newBuilder().setName("南京").setAge(34).setAddress("上海市").build();

        ctx.channel().writeAndFlush(student);
    }
}
