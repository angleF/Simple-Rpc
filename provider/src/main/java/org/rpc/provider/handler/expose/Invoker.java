package org.rpc.provider.handler.expose;

import lombok.Data;

import javax.jws.Oneway;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * @author zhaoliang.fu
 * @version V1.0
 * @Package org.rpc.provider.handler.expose
 * @Description:
 * @date 2019/6/8 20:54
 */
@Data
public class Invoker {

    private Class<?> interfaceClass;

    private Object instance;

    private Map<String,Method> methodInstances;

}
