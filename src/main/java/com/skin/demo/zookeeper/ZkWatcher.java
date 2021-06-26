package com.skin.demo.zookeeper;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @description:
 * @author: moshiqing
 * @time: 2020/3/20 9:46
 */
//@Component
public class ZkWatcher implements Watcher {

    /**
     * 集群地址
     */
    private static final String CONNECT_ADDRES = "192.168.56.103:2181";

    private static ZooKeeper zooKeeper;
    /**
     * 超时时间
     */
    private static final int SESSIONTIME = 2000;

    public ZkWatcher() {
        createConnection(CONNECT_ADDRES, SESSIONTIME);
    }

    // zk节点、发生变更、删除、修改 、 新增 事件通知
    @Override
    public void process(WatchedEvent event) {
        Event.KeeperState keeperState = event.getState();
        // 事件类型
        Event.EventType eventType = event.getType();
        // 节点名称
        String path = event.getPath();
        System.out.println(
                "#####process()####调用####keeperState:" + keeperState + ",eventType:" + eventType + ",path:" + path);
        if (Event.KeeperState.SyncConnected == keeperState) {
            // 连接类型
            if (Event.EventType.None == eventType) {
                // 建立zk连接
                System.out.println("建立zk连接成功!");
            }
            // 创建类型
            if (Event.EventType.NodeCreated == eventType) {
                System.out.println("####事件通知,当前创建一个新的节点####路径:" + path);
            }
            // 修改类型
            if (Event.EventType.NodeDataChanged == eventType) {
                System.out.println("####事件通知,当前创建一个修改节点####路径:" + path);
            }
            // 删除类型
            if (Event.EventType.NodeDeleted == eventType) {
                System.out.println("####事件通知,当前创建一个删除节点####路径:" + path);
            }
        }
        System.out.println("####################################################");
        System.out.println();
    }

    // 创建zk连接
    private void createConnection(String connectAddres, int sessiontime) {
        try {
            zooKeeper = new ZooKeeper(connectAddres, sessiontime, this);
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    // 创建节点
    public void createNode(String path, String data) {
        try {
            String result = zooKeeper.create(path, data.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            System.out.println("创建节点成功....result:" + result);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 创建临时顺序节点
     *
     * @param path
     * @param data
     */
    public String createEphemeralNode(String path, String data) {
        try {
            String result = zooKeeper.create(path, data.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
            System.out.println("创建临时目录节点成功....result:" + result);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // 修改节点
    public void updateNode(String path, String data) {
        try {
            zooKeeper.setData(path, data.getBytes(), -1);
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    // 删除节点
    public void deleNode(String path) {
        try {
            zooKeeper.delete(path, -1);
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    // 获取节点
    public String getNode(String path) {
        String str = "";
        try {
            byte[] data = zooKeeper.getData(path, true, new Stat());
            str = new String(data);
        } catch (Exception e) {
            // TODO: handle exception
        }
        return str;
    }

    // 获取节点
    public List<String> getChildren(String path) throws Exception {
        List<String> children = zooKeeper.getChildren(path, true, new Stat());
        return children;
    }

    public void close() {
        try {
            if (zooKeeper != null) {
                zooKeeper.close();
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    public static void main(String[] args) throws Exception, InterruptedException {
        ZkWatcher zkWatcher = new ZkWatcher();
//         zkWatcher.createNode("/parent1", "6452852");
        String path = "/EphemeralNode";
        zooKeeper.exists(path, true);
//        byte[] data = zooKeeper.getData(path, true, new Stat());
//        System.out.println("查询到的子数据:"+new String(data));
        // zkWatcher.updateNode(path, "88888");
//        zkWatcher.deleNode(path);
//        zkWatcher.deleNode("/parent1/childer1");
//        zkWatcher.close();
        String ephemeralNode = zkWatcher.createEphemeralNode(path, "1");
        String node = zkWatcher.getNode(ephemeralNode);
        System.out.printf("当前临时节点" + node);

    }

}
