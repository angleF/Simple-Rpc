package org.rpc.serialize.impl;

import com.alibaba.fastjson.JSON;
import org.rpc.serialize.Serializer;
import org.rpc.serialize.SerializerAlgorithm;

/**
 * @author zhaoliang.fu
 * @version V1.0
 * @Package org.rpc.serialize.impl
 * @Description:
 * @date 2019/6/9 14:51
 */
public class FastJsonSerializer implements Serializer {


    @Override
    public byte getSerializerAlgorithm() {
        return SerializerAlgorithm.JSON;
    }

    @Override
    public byte[] serialize(Object object) {
        return JSON.toJSONBytes(object);
    }

    @Override
    public <T> T deserialize(Class<T> clazz, byte[] bytes) {
        return JSON.parseObject(bytes,clazz);
    }
}
