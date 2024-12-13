package com.example.demo1.controller;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.GeneratePresignedUrlRequest;
import org.springframework.web.bind.annotation.*;

import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
public class ConfigController {

    @GetMapping("/generate-signed-url")
    public String generateSignedUrl(@RequestParam String objectName) {
        // 初始化 OSS 客户端
        OSS ossClient = new OSSClientBuilder().build("access-1163821813125480.oss-cn-guangzhou.oss-accesspoint.aliyuncs.com",
                "LTAI5tRTt6mNJWSsKgLhBBg2", "57euejpndMjxkv4Hq6YmI1fI6KcPdf");

        // 设置签名 URL 的有效期为一个月（30天）
        Date expiration = new Date(System.currentTimeMillis() + 30L * 24 * 60 * 60 * 1000);
        // 生成预签名 URL 请求
        GeneratePresignedUrlRequest request = new GeneratePresignedUrlRequest("lyhimage", objectName);
        request.setExpiration(expiration);

        // 获取预签名 URL
        URL signedUrl = ossClient.generatePresignedUrl(request);

        // 关闭客户端
        ossClient.shutdown();

        return signedUrl.toString();
    }
}