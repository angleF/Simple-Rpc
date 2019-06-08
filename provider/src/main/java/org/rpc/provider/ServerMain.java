package org.rpc.provider;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.rpc.provider.handler.ServerInitHandler;

/**
 * @author zhaoliang.fu
 * @version V1.0
 * @Package PACKAGE_NAME
 * @Description:
 * @date 2019/6/2 23:35
 */
public class ServerMain {

    public static void main(String[] args) {
        EventLoopGroup bossLoopGroup=new NioEventLoopGroup();
        EventLoopGroup workLoopGroup=new NioEventLoopGroup();
        ServerBootstrap serverBootstrap=new ServerBootstrap();
        serverBootstrap.group(bossLoopGroup,workLoopGroup)
                .childOption(ChannelOption.SO_KEEPALIVE,true)
                .childOption(ChannelOption.TCP_NODELAY,true)
                .option(ChannelOption.SO_BACKLOG, 1024)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ServerInitHandler())
                .bind(8760);
    }
}
