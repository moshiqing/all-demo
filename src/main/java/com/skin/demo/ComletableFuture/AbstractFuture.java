package com.skin.demo.ComletableFuture;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @description:
 * @author: moshiqing
 * @time: 2020/4/29 13:42
 */
public abstract class AbstractFuture implements FutureRunnablePromise {

    private Runnable runnable = null;
    private Throwable exception;
    private Object lock = new Object();
    private Object result;

    @Override
    public boolean cancel(boolean mayInterruptIfRunning) {
        return false;
    }

    @Override
    public boolean isCancelled() {
        return false;
    }

    @Override
    public boolean isDone() {
        return false;
    }

    @Override
    public Object get() throws InterruptedException, ExecutionException {
        while (result == null) {
            synchronized (lock) {
                lock.wait();
            }
        }
        if (exception != null) {
            throw new ExecutionException(exception);
        }
        return result;
    }

    @Override
    public Object get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
        return null;
    }

    @Override
    public void run() {

    }

    @Override
    public void getCallbackWork(Runnable runnable) {
        this.runnable = runnable;
    }

    @Override
    public void executeCallbackWork() {
        if (runnable != null) {
            runnable.run();
        } else {
            System.out.println("没有回调的方法可以执行");
        }
    }

    @Override
    public void setException(Throwable result) {
        this.result = new Object();
        synchronized (lock) {
            lock.notify();
        }
        this.exception = result;
    }

    @Override
    public void setResult(Object result) {
        this.result = result;
        synchronized (lock) {
            lock.notify();
        }
    }
}
