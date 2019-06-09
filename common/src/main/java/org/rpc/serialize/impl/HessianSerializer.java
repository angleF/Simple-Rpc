package org.rpc.serialize.impl;

import com.caucho.hessian.io.Hessian2Input;
import com.caucho.hessian.io.Hessian2Output;
import org.rpc.serialize.Serializer;
import org.rpc.serialize.SerializerAlgorithm;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * @author zhaoliang.fu
 * @version V1.0
 * @Package org.rpc.serialize.impl
 * @Description:
 * @date 2019/6/9 14:38
 */
public class HessianSerializer implements Serializer {


    @Override
    public byte getSerializerAlgorithm() {
        return SerializerAlgorithm.HESSIAN2;
    }

    @Override
    public byte[] serialize(Object object) {
        ByteArrayOutputStream arrayOutputStream=new ByteArrayOutputStream();

        Hessian2Output hessian2Output=new Hessian2Output(arrayOutputStream);
        try {
            hessian2Output.writeObject(object);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return arrayOutputStream.toByteArray();
    }

    @Override
    public <T> T deserialize(Class<T> clazz, byte[] bytes) {
        if (bytes == null) {
            return null;
        }
        ByteArrayInputStream byteArrayInputStream=new ByteArrayInputStream(bytes);

        Hessian2Input hessian2Input=new Hessian2Input(byteArrayInputStream);
        Object object = null;
        try {
            object = hessian2Input.readObject(clazz);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return (T)object;
    }
}
