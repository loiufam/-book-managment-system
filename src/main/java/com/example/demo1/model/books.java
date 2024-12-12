package com.example.demo1.model;

public class books {
    private String book_id;
    private String book_name;
    private String author;
    private String collection_number;  //总数量
    private String existing_number;//现存数量
    private String price;
    private String publisher;
    private String introduction;

    public books(){}

    // 全参构造器
    public books(String bookName, String author, String collectionNumber, String existingNumber) {
        this.book_name = bookName;
        this.author = author;
        this.collection_number = collectionNumber;
        this.existing_number = existingNumber;
    }

    public String getBook_id() {
        return book_id;
    }

    public String getBook_name() {
        return book_name;
    }

    public String getAuthor() {
        return author;
    }

    public String getCollection_number() {
        return collection_number;
    }

    public String getExisting_number() {
        return existing_number;
    }

    public String getPrice() {
        return price;
    }

    public String getPublisher() {
        return publisher;
    }

    public String getIntroduction() {
        return introduction;
    }

    public int getBorrowCount(){
        // 转换字符串为整数进行计算
        int total = Integer.parseInt(collection_number);
        int existing = Integer.parseInt(existing_number);
        return total - existing;
    }

    public void setBook_id(String book_id) {
        this.book_id = book_id;
    }

    public void setBook_name(String book_name) {
        this.book_name = book_name;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setCollection_number(String collection_number) {
        this.collection_number = collection_number;
    }

    public void setExisting_number(String existing_number) {
        this.existing_number = existing_number;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }
}
