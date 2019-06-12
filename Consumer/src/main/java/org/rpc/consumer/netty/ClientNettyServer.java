package org.rpc.consumer.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.rpc.protocol.packet.PacketDecoder;
import org.rpc.protocol.packet.PacketEncoder;

/**
 * @version V1.0
 * @ClassName: org.rpc.consumer.netty.ClientNettyServer
 * @Description:
 * @author: fuzhaoliang
 * @date: 2019/6/12 15:44
 */
public class ClientNettyServer {

    public void start() {
        NioEventLoopGroup eventLoopGroup=new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(eventLoopGroup).channel(NioSocketChannel.class)
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS,5000)
                .option(ChannelOption.SO_KEEPALIVE,true)
                .option(ChannelOption.TCP_NODELAY,true)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new PacketDecoder())
                                .addLast(new PacketEncoder());
                    }
                });

        bootstrap.connect("127.0.0.1",8760);
    }
}
