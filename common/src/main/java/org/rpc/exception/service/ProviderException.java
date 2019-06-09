package org.rpc.exception.service;

import org.rpc.exception.RpcException;

/**
 * @author zhaoliang.fu
 * @version V1.0
 * @Package org.rpc.exception.service
 * @Description:
 * @date 2019/6/8 21:08
 */
public class ProviderException extends RpcException {

    public ProviderException(Integer code, String info) {
        super(code, info);
    }

    public ProviderException() {
    }

    public ProviderException(String info) {
        super(info);
    }
}
