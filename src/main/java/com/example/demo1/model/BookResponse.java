package com.example.demo1.model;

import com.huawei.shade.com.alibaba.fastjson.JSONObject;
import lombok.Data;

@Data
public class BookResponse {
    private boolean success;
    private String message;
    private JSONObject data;

    public BookResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public BookResponse(boolean success, String message, JSONObject data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }

}