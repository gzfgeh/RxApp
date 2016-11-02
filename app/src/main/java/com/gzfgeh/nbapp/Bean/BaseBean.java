package com.gzfgeh.nbapp.Bean;

import javax.inject.Inject;

public class BaseBean<T>{
    private String reason;
    private T result;
    private int error_code;

    @Inject
    public BaseBean() {}

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }
}