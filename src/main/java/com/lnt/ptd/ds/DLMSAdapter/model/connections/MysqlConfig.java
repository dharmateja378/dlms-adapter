package com.lnt.ptd.ds.DLMSAdapter.model.connections;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MysqlConfig {
    @JsonProperty("Host")
    String host;
    @JsonProperty("Port")
    String port;
    @JsonProperty("DBUser")
    String DBUser;
    @JsonProperty("Password")
    String password;
    @JsonProperty("Database")
    String Database;



    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getDBUser() {
        return DBUser;
    }

    public void setDBUser(String DBUser) {
        this.DBUser = DBUser;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDatabase() {
        return Database;
    }

    public void setDatabase(String database) {
        Database = database;
    }
    @Override
    public String toString() {
        return "MysqlConfig{" +
                "host='" + host + '\'' +
                ", port='" + port + '\'' +
                ", DBUser='" + DBUser + '\'' +
                ", password='" + password + '\'' +
                ", Database='" + Database + '\'' +
                '}';
    }
}
