package com.lnt.ptd.ds.DLMSAdapter.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;


public class Slaves {


    @JsonProperty("Assets")
    private List<Assets> assets;
    @JsonProperty("Connection")
    private Connection connection;
    public List<Assets> getAssets() {
        return assets;
    }

    public void setAssets(List<Assets> assets) {
        this.assets = assets;
    }




    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
    @Override
    public String toString() {
        return "Slaves{" +
                "assets=" + assets +
                ", connection=" + connection +
                '}';
    }



}
