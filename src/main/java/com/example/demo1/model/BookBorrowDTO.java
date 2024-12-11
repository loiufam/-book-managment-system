package com.example.demo1.model;

import lombok.Data;

@Data
public class BookBorrowDTO {
    private String bookId;
    private String bookName;
    private String imageUrl;
    private String author;
    private String publisher;
    private Long borrowCount;
}