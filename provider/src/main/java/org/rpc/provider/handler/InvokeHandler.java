package org.rpc.provider.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;
import org.rpc.protocol.RpcRequest;
import org.rpc.protocol.RpcResult;
import org.rpc.provider.handler.expose.Invoker;
import org.rpc.provider.handler.expose.ProviderContext;
import org.rpc.utils.reflect.ReflectAsmUtil;

/**
 * @author zhaoliang.fu
 * @version V1.0
 * @Package org.rpc.provider.handler
 * @Description:
 * @date 2019/6/9 16:08
 */
@Slf4j
public class InvokeHandler extends SimpleChannelInboundHandler<RpcRequest> {

    private final ProviderContext providerContext=ProviderContext.INSTANCE;


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, RpcRequest rpcRequest) throws Exception {
        String className = rpcRequest.getClassName();
        Invoker providerInstance = providerContext.getProvider(className);
        // 接口calss
        Class<?> interfaceClass = providerInstance.getInterfaceClass();
        // 提供者实例
        Object instance = providerInstance.getInstance();
        // 调用的方法名
        String methodName = rpcRequest.getMethodName();
        // 请求参数
        Object[] requestParams = rpcRequest.getParams();

        RpcResult rpcResult=new RpcResult();
        try {
            Object result = ReflectAsmUtil.invokeMethod(instance, methodName, requestParams);
            rpcResult.setResult(result);
        } catch (Throwable error) {
            log.error("invoke [].[] exception:",interfaceClass.getName(),methodName,error);
            rpcResult.setException(error);
        }
        ctx.channel().writeAndFlush(rpcResult);
    }
}
