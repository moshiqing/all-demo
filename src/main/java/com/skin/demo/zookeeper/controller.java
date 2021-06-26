package com.skin.demo.zookeeper;

import org.apache.zookeeper.data.Stat;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @description:
 * @author: moshiqing
 * @time: 2020/3/20 10:31
 */
/*
@RestController
@RequestMapping("/zk")
public class controller {

    @Autowired
    private ZkWatcher zkWatcher;

    @RequestMapping("/get")
    public String get(String path){
//        zkWatcher..exists(path, true);
//        ZkWatcher zkWatcher = new ZkWatcher();
        String data = zkWatcher.getNode(path);
        System.out.println("查询到的子数据:"+data);
        return data;
    }

    @RequestMapping("/getChildren")
    public List<String> getChildren(String path) throws  Exception{
//        zkWatcher..exists(path, true);
//        ZkWatcher zkWatcher = new ZkWatcher();
        return zkWatcher.getChildren(path);
    }

    @RequestMapping("/add")
    public String add(String path,String val){
//        ZkWatcher zkWatcher = new ZkWatcher();
        zkWatcher.createNode(path,val);
        String data = this.get(path);
        System.out.println("添加到的子数据:"+data);
        return data;
    }

    @RequestMapping("/del")
    public String del(String path){
//        ZkWatcher zkWatcher = new ZkWatcher();
        zkWatcher.deleNode(path);
        String data = this.get(path);
        System.out.println("删除的子数据:"+data);
        return data;
    }
}
*/
