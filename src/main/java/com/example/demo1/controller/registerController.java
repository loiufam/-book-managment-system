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
        String image_url = user.getString("avatarUrl");
        json.put("id","-2"); //注册
        json.put("user_id",user.getString("user_name"));
        json.put("pwd",user.getString("user_password"));
        json.put("stuID",user.getString("stuID"));
        json.put("identity",user.getIntValue("identity")); //注册身份
        json.put("image_url",image_url);

        System.out.println("后端收到的图片url:" + image_url);

        String ret = temp.parse(json);
        JSONObject result = new JSONObject();
        result.put("msg", ret);
        System.out.println(result);
        return result;
    }
}



