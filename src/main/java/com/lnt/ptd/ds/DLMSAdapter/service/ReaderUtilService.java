package com.lnt.ptd.ds.DLMSAdapter.service;

import com.lnt.ptd.ds.DLMSAdapter.adapter.DlmsReader;
import com.lnt.ptd.ds.DLMSAdapter.dto.BaseAssetUtilDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.JedisPool;

import java.util.List;
@Service
public class ReaderUtilService {
    @Autowired
    List<BaseAssetUtilDto> readWriteConfig;
    @Autowired
    JedisPool jedisPool;

    public void startReaderThread(){
        for(BaseAssetUtilDto baseAssetUtilDto: readWriteConfig){
            Thread readDeviceThread=new Thread(new DlmsReader(baseAssetUtilDto.getReadUtilDto(),jedisPool));
            readDeviceThread.start();
        }
    }



}
