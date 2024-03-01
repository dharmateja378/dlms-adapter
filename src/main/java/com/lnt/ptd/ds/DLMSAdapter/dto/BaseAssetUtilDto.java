package com.lnt.ptd.ds.DLMSAdapter.dto;

import com.lnt.ptd.ds.DLMSAdapter.model.Connection;

public class BaseAssetUtilDto {
    private String name;
    private Connection connection;
    private ReadUtilDto readUtilDto;
   private WriteUtilDto writeUtilDto;
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

    public ReadUtilDto getReadUtilDto() {
        return readUtilDto;
    }

    public void setReadUtilDto(ReadUtilDto readUtilDto) {
        this.readUtilDto = readUtilDto;
    }

    public WriteUtilDto getWriteUtilDto() {
        return writeUtilDto;
    }

    public void setWriteUtilDto(WriteUtilDto writeUtilDto) {
        this.writeUtilDto = writeUtilDto;
    }
    @Override
    public String toString() {
        return "BaseAssetUtilDto{" +
                "name='" + name + '\'' +
                ", connection=" + connection +
                ", readUtilDto=" + readUtilDto +
                ", writeUtilDto=" + writeUtilDto +
                '}';
    }
}
