package org.rpc.protocol.packet;

import lombok.Getter;
import lombok.Setter;
import org.rpc.serialize.Serializer;

/**
 * @author zhaoliang.fu
 * @version V1.0
 * @Package org.rpc.provider.handler.packet
 * @Description:
 * @date 2019/6/9 14:24
 */
@Getter
@Setter
public  class Packet {

    private Serializer serializer;

    private byte type;
}
