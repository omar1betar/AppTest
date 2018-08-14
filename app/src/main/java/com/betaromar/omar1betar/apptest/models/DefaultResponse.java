package com.betaromar.omar1betar.apptest.models;

import com.google.gson.annotations.SerializedName;

public class DefaultResponse {
    @SerializedName("error")
    private Boolean err;
    @SerializedName("message")
    private String msg;

    public DefaultResponse(Boolean err, String msg) {
        this.err = err;
        this.msg = msg;
    }

    public Boolean getErr() {
        return err;
    }

    public String getMsg() {
        return msg;
    }
}
