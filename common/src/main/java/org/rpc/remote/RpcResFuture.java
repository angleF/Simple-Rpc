package org.rpc.remote;

/**
 * @version V1.0
 * @ClassName: org.rpc.remote.RpcResFuture
 * @Description:
 * @author: fuzhaoliang
 * @date: 2019/6/13 15:34
 */
public interface RpcResFuture {

    Object get(long timeOut);

    void setResponse(Object response);

    boolean isDone();
}
