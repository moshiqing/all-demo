package com.skin.demo.ComletableFuture;

import java.util.concurrent.Future;

/**
 * 异步执行接口
 */
public interface FutureRunnablePromise extends Future, Runnable, promise {

    //**这两个方法可以理解为存入异步的结果  就是callnab
    void setResult(Object o);

    void setException(Throwable o);
}
