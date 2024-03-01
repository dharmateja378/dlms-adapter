package com.lnt.ptd.ds.DLMSAdapter.model.connections;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RedisConfig {
    @JsonProperty("IP")
    String ip;
    @JsonProperty("Port")
    String port;
    @JsonProperty("Password")
    String password;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "RedisConfig{" +
                "ip='" + ip + '\'' +
                ", port='" + port + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
