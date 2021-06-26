/*
package com.skin.demo.tablestore;

import com.alicloud.openservices.tablestore.SyncClient;
import com.alicloud.openservices.tablestore.model.*;
//import com.yunhx.mchat.config.TableStoreConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.stream.LongStream;

*/
/**
 * @description:
 * @author: moshiqing
 * @time: 2020/3/16 14:01
 * <p>
 * 操作表名
 * <p>
 * 规则拼接，私聊 发送者id+接收者id，小的在前面
 * @param fromId 发送id
 * @param toId   接收id
 * @return 创建表（不带索引）
 * <p>
 * 主键自增创建表
 * <p>
 * 原子递增mid数值
 * @param cid
 * @return 获取会话mid 或者 初始化
 * @param cid
 * @return 主键构造 专用于message
 * 用于指定mid的信息写入
 * @param cid 会话id
 * @param mid 信息id
 * @return 自动构建主键保存到redis
 * @param cid
 * @return 插入一行数据（PutRow）
 * @param primaryKey 主键
 * @param msgval 属性列的值
 * <p>
 * 获取一行数据
 * @param primaryKey
 * @return 返回范围查询
 * <p>
 * 删除某一行
 * @param primaryKeyName
 * @param pkValue
 * <p>
 * 可以通过createRequestForRetry方法再构造一个请求对失败的行进行重试。这里只给出构造重试请求的部分。
 * 推荐的重试方法是使用SDK的自定义重试策略功能，支持对batch操作的部分行错误进行重试。设定重试策略后，调用接口处即不需要增加重试代码。
 * <p>
 * 可以通过createRequestForRetry方法再构造一个请求对失败的行进行重试。这里只给出构造重试请求的部分。
 * 推荐的重试方法是使用SDK的自定义重试策略功能，支持对batch操作的部分行错误进行重试。设定重试策略后，调用接口处即不需要增加重试代码。
 *//*

@Component
@Slf4j
public class TableStoreUtil implements InitializingBean {

    @Autowired
    private TableStoreConfig tableStoreConfig;

    */
/**
 * 操作表名
 *//*

    private static String MESSAGE_TABLE_NAME = "message";

//    private static String MESSAGE_TABLE_NAME = "messageString";

//    private static String MESSAGE_TABLE_NAME = "messageAUTOINCREMENT";


//    public static final String CHAT = "chat:";
//
//    public static String CID = CHAT + "cid:";

    public static SyncClient syncClient;

//    private static RedisTemplate redisTemplate;
//
//    @Resource
//    public void setRedisTemplate(RedisTemplate redisTemplate) {
//        TableStoreUtil.redisTemplate = redisTemplate;
//    }

    @Override
    public void afterPropertiesSet() throws Exception {
        syncClient = new SyncClient(tableStoreConfig.getEndpoint(), tableStoreConfig.getAccessKeyId(), tableStoreConfig.getAccesskeySecret(), tableStoreConfig.getInstanceName());
    }

    */
/**
 * 规则拼接，私聊 发送者id+接收者id，小的在前面
 *
 * @param fromId 发送id
 * @param toId   接收id
 * @return
 *//*

    public static String createSingleCid(Long fromId, Long toId) {
        return fromId < toId ? fromId + "+" + toId : toId + "+" + fromId;
    }

    */
/**
 * 创建表（不带索引）
 *//*

    public static void createTable() {
        TableMeta tableMeta = new TableMeta(MESSAGE_TABLE_NAME);
        // 为主表添加主键列。
        tableMeta.addPrimaryKeyColumn(new PrimaryKeySchema("cid", PrimaryKeyType.STRING));
        // 为主表添加主键列。
        tableMeta.addPrimaryKeyColumn(new PrimaryKeySchema("mid", PrimaryKeyType.INTEGER));
        // 数据的过期时间，单位秒, -1代表永不过期，例如设置过期时间为一年, 即为 365 * 24 * 3600。
        int timeToLive = -1;
        // 保存的最大版本数，设置为3即代表每列上最多保存3个最新的版本。
        int maxVersions = 3;
        TableOptions tableOptions = new TableOptions(timeToLive, maxVersions);
        CreateTableRequest request = new CreateTableRequest(tableMeta, tableOptions);
        // 设置读写预留值，容量型实例只能设置为0，高性能实例可以设置为非零值。
        request.setReservedThroughput(new ReservedThroughput(new CapacityUnit(0, 0)));
        CreateTableResponse table = syncClient.createTable(request);
        System.out.println("表已经创建了!!!!!:"+table.toString());
    }

    */
/**
 * 主键自增创建表
 *//*

    public static void createTableAUTOINCREMENT() {
        TableMeta tableMeta = new TableMeta(MESSAGE_TABLE_NAME);

        // 第一列为分区建
        tableMeta.addPrimaryKeyColumn(new PrimaryKeySchema("cid", PrimaryKeyType.STRING));

        // 第二列为自增列，类型为INTEGER，属性为AUTO_INCREMENT
        tableMeta.addPrimaryKeyColumn(new PrimaryKeySchema("mid", PrimaryKeyType.INTEGER, PrimaryKeyOption.AUTO_INCREMENT));

        int timeToLive = -1;  // 永不过期
        int maxVersions = 1;  // 只保存一个版本

        TableOptions tableOptions = new TableOptions(timeToLive, maxVersions);

        CreateTableRequest request = new CreateTableRequest(tableMeta, tableOptions);

        syncClient.createTable(request);
    }

    */
/**
 * 原子递增mid数值
 * @param cid
 * @return
 *//*

//    public static Long incMid(String cid){
//        String redisCid = CID + cid;
//        Object o = redisTemplate.opsForValue().get(redisCid);
//        if(o==null){
//            redisTemplate.opsForValue().set(redisCid,1);
//            o=1L;
//            log.info("无会话信息键存在,构建"+redisCid);
//        }else{
//            o=redisTemplate.opsForValue().increment(redisCid,1);
//        }
//        return Long.valueOf(String.valueOf(o));
//    }

    */
/**
 * 获取会话mid 或者 初始化
 *
 * @param cid
 * @return
 *//*

//    public static Long getMid(String cid) {
//        String redisCid = CID + cid;
//        Object o = redisTemplate.opsForValue().get(redisCid);
//        if (o == null) {
//            redisTemplate.opsForValue().set(redisCid, 1);
//            return Long.valueOf(String.valueOf(redisTemplate.opsForValue().get(redisCid)));
//        }
//        Long aLong = Long.valueOf(String.valueOf(o));
//        if(aLong==0){
//            aLong=1L;
//        }
//        return o != null ? aLong : 1;
//    }

    */
/**
 * 主键构造 专用于message
 * 用于指定mid的信息写入
 *
 * @param cid 会话id
 * @param mid 信息id
 * @return
 *//*

    public static PrimaryKey messagePrimaryKeyBuilder(String cid, Long mid) {
        PrimaryKeyBuilder primaryKeyBuilder = PrimaryKeyBuilder.createPrimaryKeyBuilder();
        primaryKeyBuilder.addPrimaryKeyColumn("cid", PrimaryKeyValue.fromString(cid));
        primaryKeyBuilder.addPrimaryKeyColumn("mid", PrimaryKeyValue.fromLong(mid));
        return primaryKeyBuilder.build();
    }

    public static PrimaryKey messagePrimaryKeyBuilder(String cid, String mid) {
        PrimaryKeyBuilder primaryKeyBuilder = PrimaryKeyBuilder.createPrimaryKeyBuilder();
        primaryKeyBuilder.addPrimaryKeyColumn("cid", PrimaryKeyValue.fromString(cid));
        primaryKeyBuilder.addPrimaryKeyColumn("mid", PrimaryKeyValue.fromString(mid));
        return primaryKeyBuilder.build();
    }

    */
/**
 * 自动构建主键保存到redis
 *
 * @param cid
 * @return
 *//*

//    public static PrimaryKey messagePrimaryKeyBuilder(String cid) {
//        PrimaryKeyBuilder primaryKeyBuilder = PrimaryKeyBuilder.createPrimaryKeyBuilder();
//        primaryKeyBuilder.addPrimaryKeyColumn("cid", PrimaryKeyValue.fromString(cid));
//        primaryKeyBuilder.addPrimaryKeyColumn("mid", PrimaryKeyValue.fromLong(TableStoreUtil.incMid(cid)));
//        return primaryKeyBuilder.build();
//    }

    */
/**
 * 插入一行数据（PutRow）
 * @param primaryKey 主键
 * @param msgval 属性列的值
 *//*

    public static void putRow(PrimaryKey primaryKey,String msgval) {
        RowPutChange rowPutChange = new RowPutChange(MESSAGE_TABLE_NAME, primaryKey);
        rowPutChange.addColumn(new Column("msg",ColumnValue.fromString(msgval)));
        syncClient.putRow(new PutRowRequest(rowPutChange));
        log.info("tablestore 主键列:"+primaryKey.getPrimaryKeyColumnsMap().toString()+",写入数据:"+msgval);
    }

    */
/**
 * 获取一行数据
 * @param primaryKey
 * @return
 *//*

    public static Row getRow(PrimaryKey primaryKey) {
        // 读一行
        SingleRowQueryCriteria criteria = new SingleRowQueryCriteria(MESSAGE_TABLE_NAME, primaryKey);
        // 设置读取最新版本
        criteria.setMaxVersions(1);
        GetRowResponse getRowResponse = syncClient.getRow(new GetRowRequest(criteria));
        Row row = getRowResponse.getRow();
        log.info("读取完毕，结果为： ");
        log.info("" + row);
        return row;
    }

    */
/**
 * 返回范围查询
 *//*

    public static List<Row> getRange(String cid, Long start, Long end) {

        PrimaryKey primaryKey = messagePrimaryKeyBuilder(cid, start);
        PrimaryKey primaryKey2 = messagePrimaryKeyBuilder(cid, end + 1);

        RangeRowQueryCriteria rangeRowQueryCriteria = new RangeRowQueryCriteria(MESSAGE_TABLE_NAME);
        rangeRowQueryCriteria.setInclusiveStartPrimaryKey(primaryKey);
        rangeRowQueryCriteria.setExclusiveEndPrimaryKey(primaryKey2);
        rangeRowQueryCriteria.setMaxVersions(1);
        GetRangeResponse getRowResponse = syncClient.getRange(new GetRangeRequest(rangeRowQueryCriteria));
        List<Row> rows = getRowResponse.getRows();

        log.info("读取完毕，结果为： {}", rows);
        rows.stream().forEach(i -> log.info(i.toString()));
        return rows;
    }

    public static List<Row> getRange(String cid, String start, String end) {

        PrimaryKey startKey = messagePrimaryKeyBuilder(cid, start);
        PrimaryKey endKey = messagePrimaryKeyBuilder(cid, end);

        RangeRowQueryCriteria rangeRowQueryCriteria = new RangeRowQueryCriteria(MESSAGE_TABLE_NAME);
        rangeRowQueryCriteria.setInclusiveStartPrimaryKey(startKey);
        rangeRowQueryCriteria.setExclusiveEndPrimaryKey(endKey);

        rangeRowQueryCriteria.setMaxVersions(1);

        GetRangeResponse getRowResponse = syncClient.getRange(new GetRangeRequest(rangeRowQueryCriteria));
        List<Row> rows = getRowResponse.getRows();

        log.info("读取完毕，结果为： {}", rows);
        rows.stream().forEach(i -> log.info(i.toString()));
        return rows;
    }

    public static void describeTable(String tableName) {
        DescribeTableRequest request = new DescribeTableRequest(tableName);
        DescribeTableResponse response = syncClient.describeTable(request);
        TableMeta tableMeta = response.getTableMeta();
        log.info("表的名称：" + tableMeta.getTableName());
        log.info("表的主键：");
        for (PrimaryKeySchema primaryKeySchema : tableMeta.getPrimaryKeyList()) {
            log.info("" + primaryKeySchema);
        }
        TableOptions tableOptions = response.getTableOptions();
        log.info("表的TTL:" + tableOptions.getTimeToLive());
        log.info("表的MaxVersions:" + tableOptions.getMaxVersions());
        ReservedThroughputDetails reservedThroughputDetails = response.getReservedThroughputDetails();
        log.info("表的预留读吞吐量："
                + reservedThroughputDetails.getCapacityUnit().getReadCapacityUnit());
        log.info("表的预留写吞吐量："
                + reservedThroughputDetails.getCapacityUnit().getWriteCapacityUnit());
    }


    */
/**
 * 删除某一行
 *
 * @param primaryKeyName
 * @param pkValue
 *//*

    public static void deleteRow(String primaryKeyName, String pkValue) {
        // 构造主键
        PrimaryKeyBuilder primaryKeyBuilder = PrimaryKeyBuilder.createPrimaryKeyBuilder();
        primaryKeyBuilder.addPrimaryKeyColumn(primaryKeyName, PrimaryKeyValue.fromString(pkValue));
        PrimaryKey primaryKey = primaryKeyBuilder.build();

        RowDeleteChange rowDeleteChange = new RowDeleteChange(MESSAGE_TABLE_NAME, primaryKey);

        syncClient.deleteRow(new DeleteRowRequest(rowDeleteChange));
    }

    public static List<BatchWriteRowResponse.RowResult> batchWriteRow(String cid,int row) {
        BatchWriteRowRequest batchWriteRowRequest = new BatchWriteRowRequest();
        // 构造rowPutChange1
        for (int i=0;i<row;i++) {
//            Long mid = TableStoreUtil.incMid(cid);
            String uuid = UUID.randomUUID().toString();
//            int uuid = new Random().nextInt();
            System.out.println(uuid);
            PrimaryKeyBuilder pk1Builder=PrimaryKeyBuilder.createPrimaryKeyBuilder();
            pk1Builder.addPrimaryKeyColumn("cid", PrimaryKeyValue.fromString(cid));
            pk1Builder.addPrimaryKeyColumn("mid", PrimaryKeyValue.fromString(uuid));
            RowPutChange rowPutChange1 = new RowPutChange(MESSAGE_TABLE_NAME, pk1Builder.build());
            rowPutChange1.addColumn(new Column("msg", ColumnValue.fromString(uuid)));
            batchWriteRowRequest.addRowChange(rowPutChange1);
        }
        // 添加到batch操作中

//        // 构造rowPutChange2
//        PrimaryKeyBuilder pk2Builder = PrimaryKeyBuilder.createPrimaryKeyBuilder();
//        pk2Builder.addPrimaryKeyColumn(cid, PrimaryKeyValue.fromString("pk2"));
//        RowPutChange rowPutChange2 = new RowPutChange(MESSAGE_TABLE_NAME, pk2Builder.build());
//        // 添加一些列
//        for (int i = 0; i < 10; i++) {
//            rowPutChange2.addColumn(new Column("Col" + i, ColumnValue.fromLong(i)));
//        }
//        // 添加到batch操作中
//        batchWriteRowRequest.addRowChange(rowPutChange2);

//        // 构造rowUpdateChange
//        PrimaryKeyBuilder pk3Builder = PrimaryKeyBuilder.createPrimaryKeyBuilder();
//        pk3Builder.addPrimaryKeyColumn("cid", PrimaryKeyValue.fromString("cid"));
//        RowUpdateChange rowUpdateChange = new RowUpdateChange(MESSAGE_TABLE_NAME, pk3Builder.build());
//        // 添加一些列
//        for (int i = 0; i < 10; i++) {
//            rowUpdateChange.put(new Column("Col" + i, ColumnValue.fromLong(i)));
//        }
        // 删除一列
//        rowUpdateChange.deleteColumns("Col10");
        // 添加到batch操作中
//        batchWriteRowRequest.addRowChange(rowUpdateChange);

//        // 构造rowDeleteChange
//        PrimaryKeyBuilder pk4Builder = PrimaryKeyBuilder.createPrimaryKeyBuilder();
//        pk4Builder.addPrimaryKeyColumn("cid", PrimaryKeyValue.fromString("cid"));
//        RowDeleteChange rowDeleteChange = new RowDeleteChange(MESSAGE_TABLE_NAME, pk4Builder.build());
//        // 添加到batch操作中
//        batchWriteRowRequest.addRowChange(rowDeleteChange);

        BatchWriteRowResponse response = syncClient.batchWriteRow(batchWriteRowRequest);

        log.info("批量写入tablestore是否全部成功：" + response.isAllSucceed());
        if (!response.isAllSucceed()) {
            for (BatchWriteRowResponse.RowResult rowResult : response.getFailedRows()) {
                log.info("失败的行：" + batchWriteRowRequest.getRowChange(rowResult.getTableName(), rowResult.getIndex()).getPrimaryKey());
                log.info("失败原因：" + rowResult.getError());
            }
            */
/**
 * 可以通过createRequestForRetry方法再构造一个请求对失败的行进行重试。这里只给出构造重试请求的部分。
 * 推荐的重试方法是使用SDK的自定义重试策略功能，支持对batch操作的部分行错误进行重试。设定重试策略后，调用接口处即不需要增加重试代码。
 *//*

//            BatchWriteRowRequest retryRequest = batchWriteRowRequest.createRequestForRetry(response.getFailedRows());
            return response.getFailedRows();
        }
        return null;
    }



    public static List<BatchWriteRowResponse.RowResult> batchWriteRowAUTOINCREMENT(String cid,int row) {
        BatchWriteRowRequest batchWriteRowRequest = new BatchWriteRowRequest();
        // 构造rowPutChange1
        for (int i=0;i<row;i++) {
            String uuid = UUID.randomUUID().toString();
            PrimaryKeyBuilder pk1Builder=PrimaryKeyBuilder.createPrimaryKeyBuilder();
            pk1Builder.addPrimaryKeyColumn("cid", PrimaryKeyValue.fromString(cid));
            pk1Builder.addPrimaryKeyColumn("mid",  PrimaryKeyValue.AUTO_INCREMENT);

            RowPutChange rowPutChange1 = new RowPutChange(MESSAGE_TABLE_NAME, pk1Builder.build());
            rowPutChange1.setReturnType(ReturnType.RT_PK);

            rowPutChange1.addColumn(new Column("msg", ColumnValue.fromString(uuid)));

            batchWriteRowRequest.addRowChange(rowPutChange1);
        }

        BatchWriteRowResponse response = syncClient.batchWriteRow(batchWriteRowRequest);

        // 打印出返回的PK列
        List<BatchWriteRowResponse.RowResult> succeedRows = response.getSucceedRows();
        if (succeedRows != null) {
            succeedRows.forEach(i-> System.out.println(i.getRow().getPrimaryKey().toString()));
//            System.out.println("PrimaryKey:" + returnRow.getPrimaryKey().toString());
        }


        log.info("批量写入tablestore是否全部成功：" + response.isAllSucceed());
        if (!response.isAllSucceed()) {
            for (BatchWriteRowResponse.RowResult rowResult : response.getFailedRows()) {
                log.info("失败的行：" + batchWriteRowRequest.getRowChange(rowResult.getTableName(), rowResult.getIndex()).getPrimaryKey());
                log.info("失败原因：" + rowResult.getError());
            }
            */
/**
 * 可以通过createRequestForRetry方法再构造一个请求对失败的行进行重试。这里只给出构造重试请求的部分。
 * 推荐的重试方法是使用SDK的自定义重试策略功能，支持对batch操作的部分行错误进行重试。设定重试策略后，调用接口处即不需要增加重试代码。
 *//*

//            BatchWriteRowRequest retryRequest = batchWriteRowRequest.createRequestForRetry(response.getFailedRows());
            return response.getFailedRows();
        }
        return null;
    }

}*/
