package org.rpc.provider.handler.expose;

import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.rpc.exception.service.ProviderException;
import org.rpc.utils.reflect.ReflectAccessUtil;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author zhaoliang.fu
 * @version V1.0
 * @Package org.rpc.provider.handler.expose
 * @Description:
 * @date 2019/6/8 21:01
 */
@Slf4j
public  class ServiceHelper {

    private ProviderContext providerContext=ProviderContext.INSTANCE;


    public void export(Object o) {
        List<Class> interfaceClass = ReflectAccessUtil.getInterfaceClass(o.getClass());
        if (interfaceClass.isEmpty()) {
            String info= "必须实现接口";
            log.error(info);
            throw new ProviderException(info);
        }
        Class aClass = interfaceClass.get(0);
        String interfaceName = aClass.getName();
        Invoker invoker = new Invoker();
        invoker.setInstance(o);
        invoker.setInterfaceInstance(aClass);
        Map<String, Method> methodMap = Maps.newHashMap();
        Arrays.stream(aClass.getMethods()).forEach(method -> {
            methodMap.put(method.getName(),method);
        });
        providerContext.addInstance(interfaceName,invoker);
    }

    public void export(List<Object> objects) {
        objects.forEach(o -> this.export(o));
    }

}
