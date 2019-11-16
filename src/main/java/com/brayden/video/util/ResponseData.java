package com.brayden.video.util;

import java.io.Serializable;
import java.util.Collection;

public class ResponseData implements Serializable {

    private boolean success;

    private String message;

    private Collection<?> data;

    public ResponseData(boolean success, String message, Collection<?> data){
        this.success = success;
        this.message = message;
        this.data = data;
    }

    public ResponseData(String message){
        this(true, message, null);
    }

    public ResponseData(Collection<?> data){
        this(true, null, data);
    }

    public ResponseData(String message, Collection<?> data){
        this(true, message, data);
    }

    public ResponseData(boolean success, String message){
        this(success, message, null);
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Collection<?> getData() {
        return data;
    }

    public void setData(Collection<?> data) {
        this.data = data;
    }
}
