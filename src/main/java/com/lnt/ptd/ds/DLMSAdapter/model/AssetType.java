package com.lnt.ptd.ds.DLMSAdapter.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AssetType {
    @JsonProperty("AssetTypeName")
    String assetTypeName;
    @JsonProperty("AssetTypeId")
    String assetTypeId;



    public String getAssetTypeName() {
        return assetTypeName;
    }

    public void setAssetTypeName(String assetTypeName) {
        this.assetTypeName = assetTypeName;
    }

    public String getAssetTypeId() {
        return assetTypeId;
    }

    public void setAssetTypeId(String assetTypeId) {
        this.assetTypeId = assetTypeId;
    }
    @Override
    public String toString() {
        return "AssetType{" +
                "assetTypeName='" + assetTypeName + '\'' +
                ", assetTypeId='" + assetTypeId + '\'' +
                '}';
    }



}
