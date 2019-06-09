package org.rpc.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author zhaoliang.fu
 * @version V1.0
 * @Package org.rpc.exception
 * @Description:
 * @date 2019/6/8 18:02
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RpcException extends RuntimeException {

    private Integer code;

    private String info;

    public RpcException(String info) {
        super(info);
        this.info = info;
    }


}
