package com.example.demo1.controller;


import com.example.demo1.model.BookBorrowDTO;
import com.example.demo1.model.BookResponse;
import com.example.demo1.model.books;
import com.example.demo1.service.BookService;
import com.example.demo1.service.parseToSQL;
import com.huawei.shade.com.alibaba.fastjson.JSONArray;
import com.huawei.shade.com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@Controller
public class userController {

    @PostMapping("/userOp")
    @ResponseBody
    public JSONObject userOp(@RequestBody(required=false) JSONObject jsonObject) throws SQLException {
        if (jsonObject == null || jsonObject.isEmpty()) {
            return createErrorResponse("无效的请求数据");
        }
        try {
            parseToSQL temp = new parseToSQL();
            String ret = temp.parse(jsonObject);
            JSONObject result = new JSONObject();
            if (ret != null && ret.length() != 0) {
                result.put("status", "success");
                result.put("msg", "操作成功");
            } else {
                result.put("status", "error");
                result.put("msg", "操作失败");
            }

            // 打印日志信息
            System.out.println("用户操作响应: " + result);

            return result;
        } catch (SQLException e){
            // 记录异常信息到日志中
            e.printStackTrace();
            return createErrorResponse("数据库操作失败：" + e.getMessage());
        }catch (Exception e) {
            // 其他未知异常
            e.printStackTrace();
            return createErrorResponse("服务器内部错误：" + e.getMessage());
        }
    }

    @GetMapping("/user_books")
    @ResponseBody
    public JSONObject getUserBooks(@RequestParam(value = "userId", required = true) String userId){
        try {
            //根据userId查询records表
            parseToSQL temp = new parseToSQL();

            List<BookBorrowDTO> books = temp.queryRecordsByID(userId);
            JSONObject response = new JSONObject();
            if (books.isEmpty()) {
                response.put("status", "success");
                response.put("msg", "您还没有借阅任何书籍。");
                response.put("data", new JSONArray()); // 返回空数组表示没有书籍
            } else {
                JSONArray bookArray = new JSONArray();
                for (BookBorrowDTO book : books) {
                    JSONObject bookJson = new JSONObject();
                    bookJson.put("title", book.getBookName());
                    bookJson.put("author", book.getAuthor());
                    bookJson.put("publisher", book.getPublisher());
                    bookJson.put("borrowCount", book.getBorrowCount());
                    bookJson.put("imageUrl", book.getImageUrl());
                    bookArray.add(bookJson);
                }
                response.put("status", "success");
                response.put("msg", "获取书籍信息成功");
                response.put("data", bookArray);
            }

            // 打印日志信息
            System.out.println("获取用户书籍响应: " + response);

            return response;
        } catch (Exception e) {
            e.printStackTrace();
            return createErrorResponse("服务器内部错误：" + e.getMessage());
        }
    }

    private JSONObject createErrorResponse(String message) {
        JSONObject errorResponse = new JSONObject();
        errorResponse.put("status", "error");
        errorResponse.put("msg", message);
        return errorResponse;
    }

    @Autowired
    private BookService bookService;

    @GetMapping("/get-book-details")
    @ResponseBody
    public ResponseEntity<BookResponse> getBookDetails(@RequestParam("book_id") String bookId) {
        try{
            JSONObject book = bookService.getBookDetails(bookId);
            return new ResponseEntity<>(new BookResponse(true, "成功", book), HttpStatus.OK);
        }catch (Exception r){
            // 处理异常
            return new ResponseEntity<>(new BookResponse(false, "服务器错误，请稍后再试"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
