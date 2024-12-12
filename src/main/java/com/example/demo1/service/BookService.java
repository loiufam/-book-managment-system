package com.example.demo1.service;


import com.example.demo1.model.books;
import com.huawei.shade.com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.SQLException;

@Service
public class BookService {
    public JSONObject getBookDetails(String bookId) throws SQLException {
        if (bookId == null || bookId.isEmpty()) {
            throw new IllegalArgumentException("图书ID不能为空");
        }
        parseToSQL temp = new parseToSQL();
        return temp.getBookDetails(bookId);
    }
}
