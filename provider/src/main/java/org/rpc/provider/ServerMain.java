package org.rpc.provider;

import com.google.common.io.Files;
import com.google.common.reflect.ClassPath;
import org.apache.commons.lang3.ClassPathUtils;
import org.rpc.provider.handler.expose.ServiceHelper;
import org.rpc.provider.netty.Netty4Server;

import java.util.Properties;

/**
 * @author zhaoliang.fu
 * @version V1.0
 * @Package PACKAGE_NAME
 * @Description:
 * @date 2019/6/2 23:35
 */
public class ServerMain {

    public static void main(String[] args) {

//        String qualifiedName = ClassPathUtils.toFullyQualifiedPath(ServerMain.class, "sds");
//        Files.
//        System.err.println(qualifiedName);
        ServiceHelper serviceHelper = new ServiceHelper();


        Netty4Server netty4Server = new Netty4Server();
        netty4Server.start();
    }
}
