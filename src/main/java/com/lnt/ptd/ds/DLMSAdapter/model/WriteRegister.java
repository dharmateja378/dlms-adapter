package com.lnt.ptd.ds.DLMSAdapter.model;

import com.fasterxml.jackson.annotation.JsonProperty;


import java.util.Map;

public class WriteRegister {
    @JsonProperty("name")
    String name;
    @JsonProperty("instance_id")
    String instance_id;
    @JsonProperty("class_id")
    int class_id;
    @JsonProperty("attribute_id")
    int attribute_id;
    @JsonProperty("dataType")
    String DataType;
    @JsonProperty("scalar_unit")
    Map<String,String> scalar_unit;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInstance_id() {
        return instance_id;
    }

    public void setInstance_id(String instance_id) {
        this.instance_id = instance_id;
    }

    public int getClass_id() {
        return class_id;
    }

    public void setClass_id(int class_id) {
        this.class_id = class_id;
    }

    public int getAttribute_id() {
        return attribute_id;
    }

    public void setAttribute_id(int attribute_id) {
        this.attribute_id = attribute_id;
    }

    public String getDataType() {
        return DataType;
    }

    public void setDataType(String dataType) {
        DataType = dataType;
    }

    public Map<String, String> getScalar_unit() {
        return scalar_unit;
    }

    public void setScalar_unit(Map<String, String> scalar_unit) {
        this.scalar_unit = scalar_unit;
    }


    @Override
    public String toString() {
        return "WriteRegister{" +
                "name='" + name + '\'' +
                ", instance_id='" + instance_id + '\'' +
                ", class_id=" + class_id +
                ", attribute_id=" + attribute_id +
                ", DataType='" + DataType + '\'' +
                ", scalar_unit=" + scalar_unit +
                '}';
    }
}
