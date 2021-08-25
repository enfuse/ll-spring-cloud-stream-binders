package io.enfuse.springcloudstreamdemo.dto;

public class ResponseData {
    private String data;

    public ResponseData(String data) {
        this.data = data;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
