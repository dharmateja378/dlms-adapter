package com.lnt.ptd.ds.DLMSAdapter.model;

import com.fasterxml.jackson.annotation.JsonProperty;



public class Connection {
    @JsonProperty("ip")
    String ip;
    @JsonProperty("port")
    int port;
    @JsonProperty("password")
    String password;
    @JsonProperty("ReferencingModel")
    String referencingModel;
    @JsonProperty("AuthenticationMechanism")
    String authenticationMechanism;
    @JsonProperty("EncryptionMechanism")
    String encryptionMechanism;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getReferencingModel() {
        return referencingModel;
    }

    public void setReferencingModel(String referencingModel) {
        this.referencingModel = referencingModel;
    }

    public String getAuthenticationMechanism() {
        return authenticationMechanism;
    }

    public void setAuthenticationMechanism(String authenticationMechanism) {
        this.authenticationMechanism = authenticationMechanism;
    }

    public String getEncryptionMechanism() {
        return encryptionMechanism;
    }

    public void setEncryptionMechanism(String encryptionMechanism) {
        this.encryptionMechanism = encryptionMechanism;
    }
    @Override
    public String toString() {
        return "Connection{" +
                "ip='" + ip + '\'' +
                ", port=" + port +
                ", password='" + password + '\'' +
                ", referencingModel='" + referencingModel + '\'' +
                ", authenticationMechanism='" + authenticationMechanism + '\'' +
                ", encryptionMechanism='" + encryptionMechanism + '\'' +
                '}';
    }



}
