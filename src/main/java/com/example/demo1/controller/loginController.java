package com.example.demo1.controller;

import com.huawei.shade.com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.example.demo1.model.users;
import com.example.demo1.service.*;

import java.sql.SQLException;

@Controller
public class loginController {

    parseToSQL temp = new parseToSQL();

    public loginController() throws SQLException {
    }

    @RequestMapping(path="/login",method = RequestMethod.GET)
    public String login_html(){

        return "forward:login.html";
    }

    public static String USERID1;

    @RequestMapping(path = "/login",method = RequestMethod.POST)
    @ResponseBody
    public JSONObject login(@RequestBody(required=false) users user) throws SQLException {
        JSONObject json = new JSONObject();

        USERID1=user.getUser_name();
        System.out.println("当前登录用户id为："+user.getUser_name());

        // 将登陆用户学号，密码封装成一个JSON对象
        json.put("id","-1"); //登陆kind对应-1
        json.put("user_id",user.getUser_name());  //根据学号查询users表
        json.put("pwd",user.getUser_password());

        String ret = temp.parse(json);   //“0”管理员登陆；“1”普通用户登录
        JSONObject result = new JSONObject();
        result.put("msg",ret);
        System.out.println(result);
        return result;
    }
}



