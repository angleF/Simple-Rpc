package org.rpc.consumer;

import org.rpc.protocol.RpcRequest;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @version V1.0
 * @ClassName: org.rpc.consumer.ReferenceProxy
 * @Description:
 * @author: fuzhaoliang
 * @date: 2019/6/13 15:53
 */
public class ReferenceProxy implements InvocationHandler {


    private Class<?> aClass;

    public ReferenceProxy(Class<?> aClass) {
        this.aClass = aClass;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        String methodName = method.getName();
        if ("toString".equals(methodName)) {

        } else if ("equals".equals(methodName)) {

        } else if ("hashCode".equals(methodName)) {

        }
        RpcRequest rpcRequest=new RpcRequest();
        rpcRequest.setClassName(aClass.getName());
        rpcRequest.setMethodName(methodName);
        rpcRequest.setParams(args);


        return null;
    }


}
