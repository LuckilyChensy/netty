package io.netty.example.inaction.client.echo;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

/**
 * @Author： bugest
 * @Description:
 * @Date:Created in 22:49 2021/4/13
 * @Modified By:
 * ChannelHandlerContextAdapter 的每个方法都可以被重写以挂钩到事件生命周期的恰当点
 */
@ChannelHandler.Sharable
//标记该类的实例可以被多个 Channel 共享
public class EchoClientHandler
        extends SimpleChannelInboundHandler<ByteBuf> {

    // 服务器的链接已经建立之后被调用
    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        //当被通知 Channel是活跃的时候，发送一条消息
        ctx.writeAndFlush(Unpooled.copiedBuffer("Netty rocks!",
                CharsetUtil.UTF_8));
    }

    // 当从服务器接收到一条消息时被调用
    @Override
    public void channelRead0(ChannelHandlerContext ctx, ByteBuf in) {
        //记录已接收消息的转储
        System.out.println(
                "Client received: " + in.toString(CharsetUtil.UTF_8));
    }


    /**
     * 重写exceptionCaught()方法允许你对Throwable 的任何子类型做出反应
     * 在这里你记录了异常并关闭了连接
    */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx,
                                Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
