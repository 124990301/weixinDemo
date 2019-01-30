package com.example.demo.service;

import com.alibaba.fastjson.JSON;
import com.example.demo.utils.HttpUtils;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by xuyf on 2018/7/25.
 */
@Service
public class WxService {

    private final String appID = "wxc102b3f34d729b03";
    private final String appsecret = "c4d9376ef18629a6b8b81e168b27379b";
    private static String accessToken;
    private static long time;
    //获取token
    public String getAccessToken(){
        long now = System.currentTimeMillis();
        if(accessToken == null || (now - time)/1000 > 7200){
            String result = HttpUtils.doGet("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+appID+"&secret="+appsecret);
            Map<String,Object> map = JSON.parseObject(result);
            accessToken = map.get("access_token").toString();
            time = System.currentTimeMillis();
        }
        return accessToken;
    }

    //设置所属行业
    public void sshy(){
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("industry_id1","2");
        map.put("industry_id2","22");
        String result = HttpUtils.doPost("https://api.weixin.qq.com/cgi-bin/template/api_set_industry?access_token="+getAccessToken(),JSON.toJSONString(map));
    }
}
