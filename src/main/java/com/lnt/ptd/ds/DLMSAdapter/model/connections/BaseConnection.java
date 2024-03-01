package com.lnt.ptd.ds.DLMSAdapter.model.connections;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BaseConnection {
    @JsonProperty("Configuration_For")
    String Configuration_for;
    @JsonProperty("Process")
    String process;
    @JsonProperty("Connections")
    Connections connections;



    public String getConfiguration_for() {
        return Configuration_for;
    }

    public void setConfiguration_for(String configuration_for) {
        Configuration_for = configuration_for;
    }

    public String getProcess() {
        return process;
    }

    public void setProcess(String process) {
        this.process = process;
    }

    public Connections getConnections() {
        return connections;
    }

    public void setConnections(Connections connections) {
        this.connections = connections;
    }
    @Override
    public String toString() {
        return "BaseConnection{" +
                "Configuration_for='" + Configuration_for + '\'' +
                ", process='" + process + '\'' +
                ", connections=" + connections +
                '}';
    }




}
