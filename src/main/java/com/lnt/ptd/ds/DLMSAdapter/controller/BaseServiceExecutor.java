package com.lnt.ptd.ds.DLMSAdapter.controller;


import com.lnt.ptd.ds.DLMSAdapter.service.ReaderUtilService;
import com.lnt.ptd.ds.DLMSAdapter.service.WriterUtilService;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
@Slf4j

public class BaseServiceExecutor {
    @Autowired
    ReaderUtilService readerUtilService;
    @Autowired
    WriterUtilService writerUtilService;

    int count=0;

    @PostConstruct
    public void start_adapter() throws InterruptedException{
        try {
                readerUtilService.startReaderThread();
                writerUtilService.startWriterThread();
        }
        catch (Exception e)
        {
            log.error("failed to start the base service... waiting for 5 seconds to restart the base service : " + e.getMessage());
            e.printStackTrace();
            Thread.sleep(6000);
            if (count++ == 60000)
            {
                log.error("Waited maximum time to restart the service. So Closing the application now.");
                log.error("Check all The Connection Parameters");
                System.exit(0);
                return;
            }
            start_adapter();
        }

    }


}
