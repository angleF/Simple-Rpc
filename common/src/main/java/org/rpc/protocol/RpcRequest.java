package org.rpc.protocol;

import lombok.Getter;
import lombok.Setter;
import org.rpc.protocol.Packet;

import java.util.Map;

/**
 * @author zhaoliang.fu
 * @version V1.0
 * @Package org.rpc.protocol.request
 * @Description:
 * @date 2019/6/8 20:34
 */
@Getter
@Setter
public class RpcRequest extends Packet {

    private Class<?>[] paramTypes;

    private Object[] params;

    private String methodName;

    private String className;

    private Map<String,Object> attributes;
}
