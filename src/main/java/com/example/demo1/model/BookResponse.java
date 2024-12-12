package com.example.demo1.model;

import lombok.Data;

@Data
public class BookResponse {
    private boolean success;
    private String message;
    private books data;

    public BookResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public BookResponse(boolean success, String message, books data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }

}