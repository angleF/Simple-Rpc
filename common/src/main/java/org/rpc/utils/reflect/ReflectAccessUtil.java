package org.rpc.utils.reflect;

import com.google.common.collect.Lists;
import org.rpc.exception.service.ProviderException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @author zhaoliang.fu
 * @version V1.0
 * @Package org.rpc.utils.reflect
 * @Description:
 * @date 2019/6/8 21:40
 */
public final class ReflectAccessUtil {

    private static final Logger logger = LoggerFactory.getLogger(ReflectAccessUtil.class);

    public static List<Class> getClassList(Object instance) {
        if (instance == null) {
                String info= "instance 不能为空";
                logger.error(info);
                throw new ProviderException(info);
        }
        Class<?> instanceClass = instance.getClass();
        List<Class> superClass = getSuperClass(instanceClass);
        List<Class> interfaces = getInterfaceClass(instanceClass);
        List<Class> classArrayList = Lists.newArrayList(superClass);
        classArrayList.addAll(Lists.newArrayList(interfaces));
        return classArrayList;
    }

    public static List<Class> getSuperClass(Class<?> instanceClass) {
        if (instanceClass == null) {
            String info= "instanceClass 不能为空";
            logger.error(info);
            throw new ProviderException(info);
        }
        Class<?> superclass = instanceClass.getSuperclass();
        return Lists.newArrayList(superclass);
    }

    public static List<Class> getInterfaceClass(Class<?> instanceClass) {
        if (instanceClass == null) {
            String info= "instanceClass 不能为空";
            logger.error(info);
            throw new ProviderException(info);
        }
        Class<?>[] interfaces = instanceClass.getInterfaces();
        return Lists.newArrayList(interfaces);
    }

}
