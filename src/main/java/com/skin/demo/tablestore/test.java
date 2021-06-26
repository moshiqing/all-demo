package com.skin.demo.tablestore;

import com.alicloud.openservices.tablestore.SyncClient;
import com.alicloud.openservices.tablestore.model.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

/**
 * @description:
 * @author: moshiqing
 * @time: 2020/4/10 10:34
 */
@Slf4j
@RestController
@RequestMapping("/tablestore")
public class test {

    @RequestMapping("/test")
    public void insert() throws Exception {

//        TableStoreUtil.createTable();
//        Thread.sleep(5);
//        TableStoreUtil.batchWriteRowAUTOINCREMENT("17",50);
//        TableStoreUtil.getRange("17","cba5f9c5-6eab-4b8d-8d56-2ce6126885b7","e306734c-f708-4e2a-8aa2-e6bcade48765");
    }
}
