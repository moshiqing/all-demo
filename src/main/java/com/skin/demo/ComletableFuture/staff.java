package com.skin.demo.ComletableFuture;

/**
 * @description: 员工
 * @author: moshiqing
 * @time: 2020/4/29 13:44
 */
public class staff extends AbstractFuture {
    private String name;
    private Object result;

    staff(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        Throwable throwable = null;
        try {
            System.out.println(name + "开始做事了！");
            Demo.chuishui();
            System.out.println(name + "做完了事！");
            this.executeCallbackWork();
            result = new Object();
        } catch (Exception e) {
            throwable = e;
        }
        if (throwable == null) {
            setResult(result);
        } else {
            setException(throwable);
        }
    }
}
