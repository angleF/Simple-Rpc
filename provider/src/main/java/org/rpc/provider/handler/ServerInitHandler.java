package org.rpc.provider.handler;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.rpc.protocol.packet.PacketDecoder;
import org.rpc.protocol.packet.PacketEncoder;

/**
 * @version V1.0
 * @ClassName: org.rpc.provider.handler.ServerInitHandler
 * @Description:
 * @author: fuzhaoliang
 * @date: 2019/6/3 15:51
 */
public class ServerInitHandler extends ChannelInitializer<NioSocketChannel> {
    @Override
    protected void initChannel(NioSocketChannel ch) throws Exception {
        ch.pipeline().addLast(new PacketDecoder())
                .addLast(new InvokeHandler())
                .addLast(new PacketEncoder());
    }
}
