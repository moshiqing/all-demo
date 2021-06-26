package com.skin.demo.FastDFS;

import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.domain.ThumbImageConfig;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileInputStream;

/**
 * @description:
 * @author: moshiqing
 * @time: 2020/3/27 9:42
 */
@RequestMapping("/fastfds")
@RestController
public class FDcontroller {

    @Resource
    private FastFileStorageClient storageClient;

    @Autowired
    private ThumbImageConfig thumbImageConfig;

    @RequestMapping("/uploadFile")
    public void uploadFile() throws Exception {
        // 要上传的文件
        File file = new File("C:\\Users\\Administrator\\Desktop\\0e41f5ff617561a0885ca34fd994917.jpg");
        // 上传并保存图片，参数：1-上传的文件流 2-文件的大小 3-文件的后缀 4-可以不管他
        StorePath storePath = this.storageClient.uploadFile(
                new FileInputStream(file), file.length(), "jpg", null);
        // 带分组的路径
        System.out.println("带分组的路径:" + storePath.getFullPath());
        // 不带分组的路径
        System.out.println("带不分组的路径:" + storePath.getPath());
        String path = thumbImageConfig.getThumbImagePath(storePath.getPath());
        System.out.println(path);
    }
}
