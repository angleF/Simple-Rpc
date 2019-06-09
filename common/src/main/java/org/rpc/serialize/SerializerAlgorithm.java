package org.rpc.serialize;

public interface SerializerAlgorithm {


    /**
     * Hessian2 序列化
     */
    byte HESSIAN2 = 0;

    /**
     * protostuff 序列化
     */
    byte PROTOSTUFF = 1;

    /**
     * json 序列化
     */
    byte JSON = 2;
}
