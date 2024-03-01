package com.lnt.ptd.ds.DLMSAdapter.model;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class BaseConfig {
    @JsonProperty("Configuration_For")
    String configuration_for;
    @JsonProperty("Process")
    String process;
    @JsonProperty("Slaves")
    List<Slaves> slavesList;

    public String getConfiguration_for() {
        return configuration_for;
    }

    public void setConfiguration_for(String configuration_for) {
        this.configuration_for = configuration_for;
    }

    public String getProcess() {
        return process;
    }

    public void setProcess(String process) {
        this.process = process;
    }

    public List<Slaves> getSlavesList() {
        return slavesList;
    }

    public void setSlavesList(List<Slaves> slavesList) {
        this.slavesList = slavesList;
    }
    @Override
    public String toString() {
        return "BaseConfig{" +
                "configuration_for='" + configuration_for + '\'' +
                ", process='" + process + '\'' +
                ", slavesList=" + slavesList +
                '}';
    }
}
