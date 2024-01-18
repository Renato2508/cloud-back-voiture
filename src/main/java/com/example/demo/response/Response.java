package com.example.demo.response;

public class Response {
    String information;
    Object object;
    boolean error;

    public String getInformation() {
        return information;
    }
    public void setInformation(String information) {
        this.information = information;
    }
    public Object getObject() {
        return object;
    }
    public void setObject(Object object) {
        this.object = object;
    }
    public boolean isError() {
        return error;
    }
    public void setError(boolean error) {
        this.error = error;
    }
}
