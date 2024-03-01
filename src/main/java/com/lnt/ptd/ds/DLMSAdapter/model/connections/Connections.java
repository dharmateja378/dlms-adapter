package com.lnt.ptd.ds.DLMSAdapter.model.connections;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Connections {
    @JsonProperty("Redis")
    RedisConfig redisConfig;
    @JsonProperty("MySQL")
    MysqlConfig mysqlConfig;
    public RedisConfig getRedisConfig() {
        return redisConfig;
    }

    public void setRedisConfig(RedisConfig redisConfig) {
        this.redisConfig = redisConfig;
    }

    public MysqlConfig getMysqlConfig() {
        return mysqlConfig;
    }

    public void setMysqlConfig(MysqlConfig mysqlConfig) {
        this.mysqlConfig = mysqlConfig;
    }

    @Override
    public String toString() {
        return "Connections{" +
                "redisConfig=" + redisConfig +
                ", mysqlConfig=" + mysqlConfig +
                ", mysqlConfig=" + mysqlConfig +
                '}';
    }
}
