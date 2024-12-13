package com.example.demo1.service;

import com.huawei.shade.com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
public class UserService {
    public JSONObject getUserImg(String userId) throws SQLException {
        parseToSQL temp = new parseToSQL();
        return temp.getUserImgById(userId);
    }
}
