package org.rpc.provider.handler.expose;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.ProviderException;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author zhaoliang.fu
 * @version V1.0
 * @Package org.rpc.provider.handler.expose
 * @Description:
 * @date 2019/6/8 21:02
 */
public final class ProviderContext {

    private final Logger logger= LoggerFactory.getLogger(ProviderContext.class);
    public static ProviderContext INSTANCE = new ProviderContext();

    /**
     * key:接口名（packageName.ClassName）
     */
    private ConcurrentHashMap<String,Invoker> instanceHold;

    private ProviderContext() {
        instanceHold=new ConcurrentHashMap<>();
    }

    public void addInstance(String interfaceName,Invoker instance) {
        if (interfaceName == null || interfaceName.isEmpty() || instance == null) {
            String info="interfaceName|instance 不能为空";
            logger.error(info);
            throw new ProviderException(info);
        }
        instanceHold.put(interfaceName,instance);
    }

    public void delInstance(String interfaceName) {
        if (interfaceName == null || interfaceName.isEmpty() ) {
            String info="interfaceName 不能为空";
            logger.error(info);
            throw new ProviderException(info);
        }
        instanceHold.remove(interfaceName);
    }

    public Invoker getProvider(String interfaceName) {
       return instanceHold.get(interfaceName);
    }
}
