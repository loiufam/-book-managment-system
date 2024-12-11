package com.example.demo1.controller;

import com.huawei.shade.com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.example.demo1.model.users;
import com.example.demo1.service.*;

import java.sql.SQLException;

@Controller
public class registerController {
    parseToSQL temp = new parseToSQL();

    public registerController() throws SQLException {
    }

    @RequestMapping(path="/register",method = RequestMethod.GET)
    public String register_html(){

        return "forward:register.html";
    }


    @RequestMapping(path = "/register",method = RequestMethod.POST)
    @ResponseBody
    public JSONObject register(@RequestBody(required=false) JSONObject user) throws SQLException {
        JSONObject json = new JSONObject();

        json.put("id","-2"); //注册功能
        json.put("user_id",user.getString("user_name")); //学号
        json.put("pwd",user.getString("user_password")); //密码
        json.put("stuID",user.getString("stuID"));  //姓名
        json.put("identity",user.getIntValue("identity")); //注册身份
        System.out.println("注册用户信息"+json);

        String ret = temp.parse(json);
        JSONObject result = new JSONObject();
        if(ret.equals("0")) {
            result.put("msg","0");
        }else{
            result.put("msg","1");
        }
        System.out.println(result);
        return result;
    }
}



