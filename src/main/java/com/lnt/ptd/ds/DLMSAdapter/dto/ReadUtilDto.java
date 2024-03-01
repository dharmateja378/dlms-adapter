package com.lnt.ptd.ds.DLMSAdapter.dto;

import com.lnt.ptd.ds.DLMSAdapter.model.Connection;
import com.lnt.ptd.ds.DLMSAdapter.model.ReadRegister;


import java.util.List;

public class ReadUtilDto {
    String name;
    Connection connection;
    List<ReadRegister> readPoints;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public List<ReadRegister> getReadPoints() {
        return readPoints;
    }

    public void setReadPoints(List<ReadRegister> readPoints) {
        this.readPoints = readPoints;
    }
}
