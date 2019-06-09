package org.rpc.provider.handler.packet;

import io.netty.buffer.ByteBuf;
import io.netty.handler.codec.CodecException;
import lombok.extern.slf4j.Slf4j;
import org.rpc.constant.CommonConstant;
import org.rpc.protocol.Packet;
import org.rpc.protocol.RpcRequest;
import org.rpc.protocol.RpcResult;
import org.rpc.serialize.Serializer;
import org.rpc.serialize.SerializerInstanceContext;

/**
 * @author zhaoliang.fu
 * @version V1.0
 * @Package org.rpc.provider.handler.packet
 * @Description:
 * @date 2019/6/8 17:36
 */
@Slf4j
public class PacketCodec {


    public static PacketCodec INSTANCE =new PacketCodec();

    private PacketCodec(){}

    /**
     * 魔数 16bit
     * version 1
     * type 1
     * 序列化类型 1
     * 内容长度  32
     * 正文 byte[]
     * @param byteBuf
     * @return
     */
    public Packet decode(ByteBuf byteBuf) {
        if (byteBuf.readableBytes()< 51) {
            log.error("Rpc 协议数据异常");
            throw new CodecException("Rpc 协议数据异常");
        }
        if (!magicNumExec(byteBuf.readBytes(4).array())) {
            log.error("Rpc 协议magicNum数据异常");
            throw new CodecException("Rpc 协议magicNum数据异常");
        }
        //version
        versionExec(byteBuf.readByte());
        // type
        byte requestType = byteBuf.readByte();
        Class<? extends Packet> requestClass = getRequestClass(requestType);
        // 序列化类型
        byte serializationType = byteBuf.readByte();
        Serializer serializerInstance = SerializerInstanceContext.INSTANCE.serializerInstance(serializationType);

        // 内容长度
        int length = byteBuf.readInt();
        //正文
        byte[] contentBody =new byte[length];
        byteBuf.readBytes(contentBody);
        Packet packet = serializerInstance.deserialize(requestClass, contentBody);
        packet.setType(requestType);
        packet.setSerializer(serializerInstance);
        return packet;
    }

    public void encode(ByteBuf byteBuf,Packet packet) {
        Serializer serializer = packet.getSerializer();
        byte[] bytes = serializer.serialize(packet);
        byteBuf.writeInt(CommonConstant.MAGIC_NUMBER);
        byteBuf.writeByte(0);
        byteBuf.writeByte(packet.getType());
        byteBuf.writeByte(serializer.getSerializerAlgorithm());
        byteBuf.writeInt(bytes.length);
        byteBuf.writeBytes(bytes);
    }

    protected   boolean magicNumExec(byte[] bytes) {
        return true;
    }

    protected void versionExec(byte b) {

    }

    private Class<? extends Packet> getRequestClass(byte serializationType) {
        if (serializationType == 0) {
            return RpcRequest.class;
        }else if (serializationType == 1) {
            return RpcResult.class;
        }
        return null;
    }
}
