package com.lnt.ptd.ds.DLMSAdapter.adapter;
import com.lnt.ptd.ds.DLMSAdapter.constants.AppConstants;
import com.lnt.ptd.ds.DLMSAdapter.dto.WriteUtilDto;
import com.lnt.ptd.ds.DLMSAdapter.model.*;
import com.lnt.ptd.ds.DLMSAdapter.utils.RedisInstanceUtil;
import lombok.extern.slf4j.Slf4j;
import org.openmuc.jdlms.*;
import org.openmuc.jdlms.datatypes.DataObject;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPubSub;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

/*
This program performs the writing functionality of DLMS adapter
* */
@Slf4j
public class DlmsWriter implements Runnable
{
    private final WriteUtilDto writeUtilDto;
    private final JedisPool jedisPool;
    public DlmsWriter(WriteUtilDto writeUtilDto,JedisPool jedisPool){
        this.writeUtilDto=writeUtilDto;
        this.jedisPool=jedisPool;
    }
    public void writeValue(String parameter_value,List<WriteRegister> writeRegisterList) // Method to write data to the device
    {
        TcpConnectionBuilder connectionBuilder;
        try
        {
            connectionBuilder = new TcpConnectionBuilder(InetAddress.getByName(writeUtilDto.getConnection().getIp())).setPort(writeUtilDto.getConnection().getPort());

        } catch (UnknownHostException e)
        {
            throw new RuntimeException(e);
        }

        for(WriteRegister writeRegister:writeRegisterList)
        {
            try(DlmsConnection dlmsConnection= connectionBuilder.build())
            {
                AccessResultCode getResult=dlmsConnection.set(new SetParameter(new AttributeAddress(writeRegister.getClass_id(),writeRegister.getInstance_id(),writeRegister.getAttribute_id()),DataObject.newUInteger64Data(Long.parseUnsignedLong(parameter_value))));
                if(getResult==AccessResultCode.SUCCESS)
                {
                    log.error("Inserted Value "+parameter_value+" to target device");
                }
                else
                {
                    log.error("Value Not written"+getResult.getCode());
                }

            } catch (IOException e)
            {
                throw new RuntimeException(e);
            }

        }

    }

    public void writer_processor()
    {
        Jedis jedis;
        String redisTopic = writeUtilDto.getName().concat(AppConstants.REDIS_SUB_TOPIC_SUFFIX);
        try {
            jedis = jedisPool.getResource();
            jedis.subscribe(new JedisPubSub() {
                @Override
                public void onMessage(String channel, String message) {
                    super.onMessage(channel, message);
                    List<WriteRegister> writeRegisterList = writeUtilDto.getWritePoints();
                    String[] keyValueList = message.split(",");
                    if (writeRegisterList != null && writeRegisterList.size() >= 1) {
                        for (String KeyValueStr : keyValueList)
                        {
                            String[] keyValue = KeyValueStr.split(" ");
                            String parameter_name = keyValue[0];
                            var parameter_value = keyValue[1];
                            for (WriteRegister ele : writeRegisterList)
                            {
                                if (ele.getName().contains(parameter_name))
                                {
                                    writeValue(parameter_value,  writeUtilDto.getWritePoints());
                                }
                            }
                        }

                    }
                }

                @Override
                public void onSubscribe(String channel, int subscribedChannels) {
                    super.onSubscribe(channel, subscribedChannels);
                    log.debug("----------------------------------->");
                    log.debug("Subscribing to Redis topic : " + channel);
                }

                @Override
                public void onUnsubscribe(String channel, int subscribedChannels) {
                    super.onUnsubscribe(channel, subscribedChannels);
                }


            }, redisTopic);

        } catch (Exception e) {
            jedis = RedisInstanceUtil.createRedisInstance();
            log.info("Trying to Reconnect with Redis");
            //e.printStackTrace();
            if( jedis == null ) return;
        }
    }

    @Override
    public void run() {
        writer_processor();
    }
    }

