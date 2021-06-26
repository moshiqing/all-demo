package com.skin.demo.tablestore;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: moshiqing
 * @time: 2020/4/10 10:22
 */
@Data
@ConfigurationProperties(prefix = "tablestore")
@Component
public class TableStoreConfig {

    private String accessKeyId;
    private String accesskeySecret;
    private String endpoint;
    private String instanceName;
}
