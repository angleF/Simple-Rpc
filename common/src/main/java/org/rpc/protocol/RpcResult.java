package org.rpc.protocol;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhaoliang.fu
 * @version V1.0
 * @Package org.rpc.protocol
 * @Description:
 * @date 2019/6/9 15:27
 */

@Data
public class RpcResult extends Packet {

    private Object result;

    private Throwable exception;

    private Map<String, String> attachments = new HashMap<String, String>();

    public Object recreate() throws Throwable {
        // 有异常，抛出异常
        if (exception != null) {
            throw exception;
        }
        // 无异常，返回结果
        return result;
    }
}
