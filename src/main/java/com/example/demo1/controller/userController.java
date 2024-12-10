package com.example.demo1.controller;


import com.example.demo1.service.parseToSQL;
import com.huawei.shade.com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import java.sql.SQLException;

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
            if (ret != null && ret.length() >= 2) {
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

    private JSONObject createErrorResponse(String message) {
        JSONObject errorResponse = new JSONObject();
        errorResponse.put("status", "error");
        errorResponse.put("msg", message);
        return errorResponse;
    }
}
