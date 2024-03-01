package com.lnt.ptd.ds.DLMSAdapter.utils;

import lombok.extern.slf4j.Slf4j;

import java.util.Iterator;
import java.util.Map;
@Slf4j
public class MessageBuilderUtil {
    public static String buildMessageString(Map<String,String> messageMap) {


        //Add current-time to map
        messageMap.put("epoch", Long.toString(System.currentTimeMillis()));

        try {
            Iterator<Map.Entry<String, String>> it = messageMap.entrySet().iterator();
            StringBuilder s = new StringBuilder();

            while (it.hasNext()) {
                if (!s.isEmpty()) s.append(",");
                Map.Entry<String, String> kvp = it.next();
                s.append(kvp.getKey());
                s.append(" ");
                s.append(kvp.getValue());
            }
            log.debug(s.toString());
            return s.toString();
        } catch (Exception e) {
            log.error("Error while parsing the messageMap");
            return "";
        }
    }
}
