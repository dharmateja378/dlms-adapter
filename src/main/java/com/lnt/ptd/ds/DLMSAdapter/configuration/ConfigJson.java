package com.lnt.ptd.ds.DLMSAdapter.configuration;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.lnt.ptd.ds.DLMSAdapter.constants.AppConstants;
import com.lnt.ptd.ds.DLMSAdapter.dto.BaseAssetUtilDto;
import com.lnt.ptd.ds.DLMSAdapter.dto.ReadUtilDto;
import com.lnt.ptd.ds.DLMSAdapter.dto.WriteUtilDto;
import com.lnt.ptd.ds.DLMSAdapter.model.Assets;
import com.lnt.ptd.ds.DLMSAdapter.model.BaseConfig;
import com.lnt.ptd.ds.DLMSAdapter.model.ReadRegister;
import com.lnt.ptd.ds.DLMSAdapter.model.Slaves;
import com.lnt.ptd.ds.DLMSAdapter.model.connections.BaseConnection;
import com.lnt.ptd.ds.DLMSAdapter.utils.MessageBuilderUtil;
import lombok.extern.slf4j.Slf4j;
import org.openmuc.jdlms.*;
import org.openmuc.jdlms.datatypes.DataObject;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.io.FileReader;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.Duration;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Slf4j
@Configuration
public class ConfigJson {

    public static Map<String,String> staticconnectionmap;

    @Bean
    /* Reading DLMS configuration file*/
    public BaseConfig baseConfig() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            FileReader fileReader = new FileReader("DLMSAdapter/config/dlmsconfig.json"); // Replace th json file path
            BaseConfig baseConfigObject = objectMapper.readValue(fileReader, BaseConfig.class); //Loading json file data to object based on BaseConfig class defined in model package
            System.out.println(baseConfigObject.toString());
            return baseConfigObject;
        } catch (Exception e) {
            log.error("""
                    Error while creating reading the file dlmsconfig.json.\s
                     Please check the file and restart the application.
                     Shutting Down......
                    """);
            System.exit(1);
        }
        return null;
    }
    @Bean
    public BaseConnection baseConnection(){

        ObjectMapper objectMapper=new ObjectMapper();
        try{
            FileReader fileReader=new FileReader("DLMSAdapter/config/connectionsconfig.json");
            BaseConnection baseConnection=objectMapper.readValue(fileReader,BaseConnection.class);
            System.out.println(baseConnection.toString());
          //  staticconnectionmap.put(baseConnection.getConnections().getRedisConfig().getIp(),baseConnection.getConnections().getRedisConfig().getPort());
            return  baseConnection;
        }catch (Exception e){


            e.printStackTrace();
        }
        return null;

    }
    @Bean
    List<BaseAssetUtilDto> readWriteConfig(){
        List<BaseAssetUtilDto> baseAssetUtilDtoList=new ArrayList<>();
        String slaveAssetName=null;
        for(Slaves slave:baseConfig().getSlavesList()){
            BaseAssetUtilDto baseAssetUtilDto=new BaseAssetUtilDto();
            ReadUtilDto readUtilDto=new ReadUtilDto();
            WriteUtilDto writeUtilDto=new WriteUtilDto();
            for(Assets assets:slave.getAssets()){
                slaveAssetName=assets.getAssetName();
                readUtilDto.setName(assets.getAssetName());
                readUtilDto.setReadPoints(assets.getReadRegister());
                writeUtilDto.setName(assets.getAssetName());
                writeUtilDto.setWritePoints(assets.getWriteRegisters());
            }
            readUtilDto.setConnection(slave.getConnection());
            writeUtilDto.setConnection(slave.getConnection());
            baseAssetUtilDto.setConnection(slave.getConnection());
            baseAssetUtilDto.setName(slaveAssetName);
            baseAssetUtilDto.setReadUtilDto(readUtilDto);
            baseAssetUtilDto.setWriteUtilDto(writeUtilDto);
            baseAssetUtilDtoList.add(baseAssetUtilDto);
        }
        for(BaseAssetUtilDto e:baseAssetUtilDtoList){
            System.out.println(e.toString());
        }
        return baseAssetUtilDtoList;
    }
    /*
    public List<WriteUtilDto> writeConfig(){
        List<WriteUtilDto> writeList=new ArrayList<>();
        WriteUtilDto writeUtilDto=new WriteUtilDto();
        for(Slaves slaves: baseConfig().getSlavesList()){
            for(Assets assets:slaves.getAssetsList()){
                writeUtilDto.setWriteRegisters(assets.getWriteRegisters());
                writeUtilDto.setAssetName(assets.getAssetName());
            }
            writeUtilDto.setConnection(slaves.getConnection());
            writeList.add(writeUtilDto);
        }
        for(WriteUtilDto element:writeList){
            System.out.println(element.toString());
        }
        return writeList;
    }*/

@Bean
public JedisPool jedisPool() {


   // System.out.println("Redis details loaded" + baseConnection().getConnections().getRedisConfig().toString());
    JedisPoolConfig jedisPoolConfig =  new JedisPoolConfig();
    jedisPoolConfig.getBlockWhenExhausted();
    jedisPoolConfig.setTestOnBorrow(true);
    jedisPoolConfig.setTestOnReturn(true);
    jedisPoolConfig.setTestWhileIdle(true);
    jedisPoolConfig.setMaxWait(Duration.ofMillis(-1));
    jedisPoolConfig.setBlockWhenExhausted(true);
    jedisPoolConfig.setMaxIdle(30);
    jedisPoolConfig.setMinIdle(10);

    return new JedisPool(jedisPoolConfig,baseConnection().getConnections().getRedisConfig().getIp(),Integer.parseInt(baseConnection().getConnections().getRedisConfig().getPort()),50000);
}



    }



