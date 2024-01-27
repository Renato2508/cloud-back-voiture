package com.example.demo.response;

public class Response {
    String information;
    Object object;
    boolean error;

    public Response(String information, boolean error, Object object) {
        this.information = information;
        this.object = object;
        this.error = error;
    }

    public Response() {
    }

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
