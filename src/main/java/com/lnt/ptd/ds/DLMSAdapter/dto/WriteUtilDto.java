package com.lnt.ptd.ds.DLMSAdapter.dto;

import com.lnt.ptd.ds.DLMSAdapter.model.Assets;
import com.lnt.ptd.ds.DLMSAdapter.model.Connection;
import com.lnt.ptd.ds.DLMSAdapter.model.ReadRegister;
import com.lnt.ptd.ds.DLMSAdapter.model.WriteRegister;
import lombok.Data;

import java.util.List;

public class WriteUtilDto {

    String name;
    Connection connection;
    List<WriteRegister> writePoints;
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

    public List<WriteRegister> getWritePoints() {
        return writePoints;
    }

    public void setWritePoints(List<WriteRegister> writePoints) {
        this.writePoints = writePoints;
    }
    @Override
    public String toString() {
        return "WriteUtilDto{" +
                "name='" + name + '\'' +
                ", connection=" + connection +
                ", writePoints=" + writePoints +
                '}';
    }
}
