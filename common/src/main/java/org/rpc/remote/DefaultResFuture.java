package org.rpc.remote;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @version V1.0
 * @ClassName: org.rpc.remote.DefaultResFuture
 * @Description:
 * @author: fuzhaoliang
 * @date: 2019/6/13 15:37
 */
@Slf4j
public class DefaultResFuture implements RpcResFuture {


    private volatile boolean done;

    private volatile Object response;

    Lock lock=new ReentrantLock();

    Condition condition = lock.newCondition();


    @Override
    public Object get(long timeOut) {
        if (timeOut <= 0) {
            timeOut = 1000;
        }
        long startTimeMillis = System.currentTimeMillis();
        while (!isDone()) {
            try {
                condition.await(timeOut, TimeUnit.MICROSECONDS);
                if (isDone()) {
                    break;
                } else if ((System.currentTimeMillis() - startTimeMillis) >= timeOut) {
                    log.error("invoker Provider time out");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
        return response;
    }

    @Override
    public void setResponse(Object response){
        this.done=true;
        this.response=response;
    }

    @Override
    public boolean isDone() {
        return done;
    }
}
