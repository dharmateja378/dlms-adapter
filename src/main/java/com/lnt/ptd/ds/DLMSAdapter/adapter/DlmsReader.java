package com.lnt.ptd.ds.DLMSAdapter.adapter;

import com.lnt.ptd.ds.DLMSAdapter.constants.AppConstants;
import com.lnt.ptd.ds.DLMSAdapter.dto.ReadUtilDto;
import com.lnt.ptd.ds.DLMSAdapter.model.Assets;
import com.lnt.ptd.ds.DLMSAdapter.model.ReadRegister;
import com.lnt.ptd.ds.DLMSAdapter.model.Slaves;
import com.lnt.ptd.ds.DLMSAdapter.configuration.ConfigJson;
import com.lnt.ptd.ds.DLMSAdapter.utils.MessageBuilderUtil;
import lombok.extern.slf4j.Slf4j;
import org.openmuc.jdlms.*;
import org.openmuc.jdlms.datatypes.DataObject;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.net.InetAddress;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
* This program performs the reading functionality of DLMS adapter
* */
@Slf4j
public class DlmsReader implements Runnable {

    private final ReadUtilDto readUtilDto;
    private final Jedis jedis;
    Map<String,Map<String,String>> resultMap=new HashMap<>();
   public DlmsReader(ReadUtilDto readUtilDto,JedisPool jedisPool) {
       this.readUtilDto=readUtilDto;
       this.jedis = jedisPool.getResource();
   }
    public void reader_processor() throws InterruptedException
    {
        while (true) {
            TcpConnectionBuilder connectionBuilder = null;
            try {
                connectionBuilder = new TcpConnectionBuilder(InetAddress.getByName(readUtilDto.getConnection().getIp())).setPort(readUtilDto.getConnection().getPort());
                List<ReadRegister> readRegistersList = readUtilDto.getReadPoints();
                for (ReadRegister readRegister : readRegistersList) {
                    try (DlmsConnection dlmsConnection = connectionBuilder.build()) {
                        Map<String, String> temp_value = new HashMap<>();
                        GetResult getResult = dlmsConnection.get(new AttributeAddress(readRegister.getClass_id(), readRegister.getInstance_id(), readRegister.getAttribute_id()));
                        if (getResult.getResultCode() == AccessResultCode.SUCCESS) {
                            DataObject resultData = getResult.getResultData();
                            temp_value.put(readRegister.getName(), resultData.getValue().toString());
                            resultMap.put(readUtilDto.getName(), temp_value);
                            System.out.println(resultMap.toString());
                            redis_publish(readUtilDto.getName() + AppConstants.REDIS_PUB_TOPIC_SUFFIX, MessageBuilderUtil.buildMessageString(temp_value));
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }


            } catch (Exception e) {

            }
            Thread.sleep(1000);
        }

    }
    @Override
    public void run () {
        try {
            reader_processor();
        } catch (Exception e) {
            log.error("Failed to start DLMS Reader Thread");
            e.printStackTrace();
        }
    }
    public void redis_publish(String topic_name, String message) {
                jedis.publish(topic_name, message);
        }


    }
    /*
    *
//        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
//        int initialDelay = 0;
//        int pollingFrequency = 2;


        List<Slaves> slavesList = configJson.baseConfig().getSlavesList();
        for (Slaves slaves : slavesList) {
            TcpConnectionBuilder connectionBuilder = null;
            try {
                connectionBuilder = new TcpConnectionBuilder(InetAddress.getByName(slaves.getConnection().getIp())).setPort(slaves.getConnection().getPort()); //Building connection with the DLMS device

            } catch (UnknownHostException e) {
                throw new RuntimeException(e);
            }
            TcpConnectionBuilder finalConnectionBuilder = connectionBuilder;
       //     scheduler.scheduleAtFixedRate(() -> {
                for (Assets assets : slaves.getAssets()) {
                    List<ReadRegister> readRegistersList = assets.getReadRegister();
                    for (ReadRegister readRegister : readRegistersList) {
                        try (DlmsConnection dlmsConnection = finalConnectionBuilder.build()) {
                            Map<String, String> temp_value = new HashMap<>();
                            GetResult getResult = dlmsConnection.get(new AttributeAddress(readRegister.getClass_id(), readRegister.getInstance_id(), readRegister.getAttribute_id()));
                            if (getResult.getResultCode() == AccessResultCode.SUCCESS) {
                                DataObject resultData = getResult.getResultData();
                                temp_value.put(readRegister.getName(), resultData.getValue().toString());
                                resultMap.put(assets.getAssetId(), temp_value);
                                System.out.println(resultMap.toString());
                                redis_publish(assets.getAssetId()+AppConstants.REDIS_PUB_TOPIC_SUFFIX, MessageBuilderUtil.buildMessageString(temp_value));
                            }
                        } catch (Exception e) {
                            e.printStackTrace();

                        }
                    }
                }

     //       }, initialDelay, pollingFrequency, TimeUnit.SECONDS);

        }
*/

