package com.example.demo.controller;

import com.tencent.xinge.Message;
import com.tencent.xinge.XingeApp;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by xuyf on 2018/7/28.
 */
public class XgController {
    private String accessId = "151675a352111";
    private  String secretKey = "adb043a1e37249c05ee800f701873bdc";
    private XingeApp xinge = new XingeApp(2100301217,"AY6NG9DA942X");

    @GetMapping("/xg")
    @ResponseBody
    public String send(){
        Message message = new Message();
        message.setTitle("title");
        message.setContent("content");
        message.setType(Message.TYPE_MESSAGE);
        message.setExpireTime(86400);
        JSONObject json = xinge.pushSingleDevice("",message);
        return json.toString();
    }
}
