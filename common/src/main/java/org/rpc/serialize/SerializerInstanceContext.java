package org.rpc.serialize;

import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.rpc.serialize.impl.FastJsonSerializer;
import org.rpc.serialize.impl.HessianSerializer;
import org.rpc.serialize.impl.ProtostuffSerializer;

import java.util.Map;
import java.util.ServiceLoader;

/**
 * @author zhaoliang.fu
 * @version V1.0
 * @Package org.rpc.serialize
 * @Description:
 * @date 2019/6/9 14:38
 */
@Slf4j
public final class SerializerInstanceContext {

    public static SerializerInstanceContext INSTANCE = new SerializerInstanceContext();

    private SerializerInstanceContext(){}

    private static Map<Byte,Serializer> hold = Maps.newHashMap();

    static {
        ServiceLoader<Serializer> serviceLoader = ServiceLoader.load(Serializer.class);
        serviceLoader.forEach(serializer -> hold.put(serializer.getSerializerAlgorithm(),serializer));
    }

    public Serializer serializerInstance(byte type) {
        return hold.get(type);
    }

    public Serializer serializerInstance() {
        return hold.get(SerializerAlgorithm.HESSIAN2);
    }
}
