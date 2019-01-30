package com.example.demo.controller;

import com.example.demo.utils.SHA1;
import com.example.demo.service.WxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

/**
 * Created by xuyf on 2018/7/24.
 */
@RestController
public class wxController {
    @Autowired
    WxService wxService;

    @GetMapping("/jy")
    @ResponseBody
    public String weixinjy(HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        // 微信加密签名
        String signature = request.getParameter("signature");
        // 随机字符串
        String echostr = request.getParameter("echostr");
        // 时间戳
        String timestamp = request.getParameter("timestamp");
        // 随机数
        String nonce = request.getParameter("nonce");

        String[] str = {"sbyscs", timestamp, nonce};
        Arrays.sort(str); // 字典序排序
        String bigStr = str[0] + str[1] + str[2];
        // SHA1加密
        String digest = new SHA1().getDigestOfString(bigStr.getBytes())
                .toLowerCase();

        // 确认请求来至微信
        if (digest.equals(signature)) {
           return echostr;
        }else{
            return null;
        }
    }

    @GetMapping("/token")
    @ResponseBody
    public String getAccessToken(){
       return wxService.getAccessToken();
    }

    @GetMapping("/sshy")
    public void sshy(){
        wxService.sshy();
    }


}
