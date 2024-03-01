package com.lnt.ptd.ds.DLMSAdapter.model;

import com.fasterxml.jackson.annotation.JsonProperty;


import java.util.List;


public class Assets {
    @JsonProperty("AssetType")
    private AssetType assetType;
    @JsonProperty("AssetName")
    private String assetName;
    @JsonProperty("AssetId")
    private String assetId;
    @JsonProperty("ReadRegisters")
    List<ReadRegister> readRegister;
    @JsonProperty("WriteRegisters")
    List<WriteRegister> writeRegisters;


    public AssetType getAssetType() {
        return assetType;
    }

    public void setAssetType(AssetType assetType) {
        this.assetType = assetType;
    }

    public String getAssetName() {
        return assetName;
    }

    public void setAssetName(String assetName) {
        this.assetName = assetName;
    }

    public String getAssetId() {
        return assetId;
    }

    public void setAssetId(String assetId) {
        this.assetId = assetId;
    }

    public List<ReadRegister> getReadRegister() {
        return readRegister;
    }

    public void setReadRegister(List<ReadRegister> readRegister) {
        this.readRegister = readRegister;
    }

    public List<WriteRegister> getWriteRegisters() {
        return writeRegisters;
    }

    public void setWriteRegisters(List<WriteRegister> writeRegisters) {
        this.writeRegisters = writeRegisters;
    }


    @Override
    public String toString() {
        return "Assets{" +
                "assetType=" + assetType +
                ", assetName='" + assetName + '\'' +
                ", assetId='" + assetId + '\'' +
                ", readRegister=" + readRegister +
                ", writeRegisters=" + writeRegisters +
                '}';
    }


}
