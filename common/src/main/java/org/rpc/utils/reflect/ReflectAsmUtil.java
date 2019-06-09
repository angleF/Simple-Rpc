package org.rpc.utils.reflect;

import com.esotericsoftware.reflectasm.MethodAccess;
import org.apache.commons.lang3.StringUtils;
import org.rpc.exception.service.ProviderException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author zhaoliang.fu
 * @version V1.0
 * @Package org.rpc.utils.reflect
 * @Description:
 * @date 2019/6/8 21:24
 */
public final class ReflectAsmUtil {

    private static final Logger logger = LoggerFactory.getLogger(ReflectAsmUtil.class);


//    public static <T> Object invokeMethod(Class<T> classObj,Object instance, String methodName,Object... params) {
//        if (classObj == null || instance == null || StringUtils.isBlank(methodName)) {
//            String info= "classObj|instance|methodName 不能为空";
//            logger.error(info);
//            throw new ProviderException(info);
//        }
//        if (!classObj.isAssignableFrom(instance.getClass())) {
//            String info= "classObj 非 instance 超类或接口";
//            logger.error(info);
//            throw new ProviderException(info);
//        }
//        MethodAccess methodAccess = MethodAccess.get(classObj);
//        return methodAccess.invoke(instance, methodName, params);
//    }

    public static  Object invokeMethod(Object instance, String methodName, Object... params) {
        if ( instance == null || StringUtils.isBlank(methodName)) {
            String info= "instance|methodName 不能为空";
            logger.error(info);
            throw new ProviderException(info);
        }
        Class<?> instanceClass = instance.getClass();
        MethodAccess methodAccess = MethodAccess.get(instanceClass);
        return methodAccess.invoke(instance, methodName, params);
    }
}
