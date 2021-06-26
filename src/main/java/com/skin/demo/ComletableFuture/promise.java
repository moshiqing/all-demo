package com.skin.demo.ComletableFuture;

/**
 * @description:
 * @author: moshiqing
 * @time: 2020/4/29 13:43
 */

/**
 * 异步执行后的回调接口
 */
public interface promise {
    /**
     * 获取回调的结果
     *
     * @param runnable
     */
    void getCallbackWork(Runnable runnable);

    /**
     * 执行回调接口
     */
    void executeCallbackWork();
}
