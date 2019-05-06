package com.springboot.controller;

import com.springboot.entity.OrderEntity;
import com.springboot.test.TokenUtil;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class OrderController {

    @RequestMapping("/getToken")
    public String getToken(){
        return TokenUtil.getToken();
    }
//    @RequestMapping(value = "/addOrder",produces = "application/json,charset=utf-8")
    public String addOrder(@RequestBody OrderEntity orderEntity, HttpServletRequest request){
        //获取令牌存放在请求头中
     String token =request.getHeader("token");
     if (StringUtils.isEmpty(token)){
         return "参数错误";
     }
     if (!TokenUtil.findToken(token)){
         return "请勿重复提交！";
     }
     //保存数据到数据库中
//     int result=orderMapper.addOrder(orderEntity);
     int result=0;
        return result>0?"添加成功":"添加失败";
    }
}
