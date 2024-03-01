package com.lnt.ptd.ds.DLMSAdapter.service;

import com.lnt.ptd.ds.DLMSAdapter.adapter.DlmsReader;
import com.lnt.ptd.ds.DLMSAdapter.adapter.DlmsWriter;
import com.lnt.ptd.ds.DLMSAdapter.dto.BaseAssetUtilDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.JedisPool;

import java.util.List;
@Service
public class WriterUtilService {
    @Autowired
    List<BaseAssetUtilDto> readWriteConfig;
    @Autowired
    JedisPool jedisPool;

    public void startWriterThread(){
        for(BaseAssetUtilDto baseAssetUtilDto: readWriteConfig){
            Thread writeDeviceThread=new Thread(new DlmsWriter(baseAssetUtilDto.getWriteUtilDto(),jedisPool));
            writeDeviceThread.start();
        }
    }
}
