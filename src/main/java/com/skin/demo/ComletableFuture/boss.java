package com.skin.demo.ComletableFuture;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * @description: 老板
 * @author: moshiqing
 * @time: 2020/4/29 13:43
 */
public class boss extends AbstractFuture {

    private String name;
    public List<FutureRunnablePromise> stafflist = Lists.newArrayList();

    boss(String name) {
        this.name = name;
    }

    public void setStafflist(FutureRunnablePromise s) {
        stafflist.add(s);
    }

    @Override
    public void run() {
        System.out.println(name + "开始做事了！");
        System.out.println(name + "中间有一些骚操作");
        stafflist.forEach(staff -> {
            try {
                Object o = staff.get();
                System.out.println("员工做了一些事情 返回了一些结果" + o.toString());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        });
        System.out.println(name + "做完了事,得到人家的成果之后！");
    }
}
