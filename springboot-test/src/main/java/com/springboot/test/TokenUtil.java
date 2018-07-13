package com.springboot.test;

import org.springframework.util.StringUtils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class TokenUtil {

    private static Map<String,Object> tokenMap=new ConcurrentHashMap<String, Object>();
    //1、什么Token(令牌) 表示是一个临时不允许有重复相同的值（临时且唯一）
    //2、使用令牌方式防止Token重复提交

    // 使用场景：在调用第三方api接口的时候，需要传递令牌，该api接口获取到令牌之后，执行当前业务逻辑，然后把当前令牌删除掉。

    //代码步骤：
    //1、获取令牌
    //2、判断令牌是否在缓存中有对应的数据
    //3、如果缓存中没有该令牌的话，直接报错（请勿重复提交）
    //4、如果缓存中有该令牌的话，直接执行该业务逻辑
    //5、执行完该业务逻辑后，直接删除掉令牌

    //获取令牌
    public static synchronized String getToken(){
        //如果在分布式场景下使用分布式全局ID实现
        String token="token"+System.currentTimeMillis();
        tokenMap.put(token,token);
        return token;
    }
    public static boolean findToken(String tokenKey){
        //判断令牌是否在tokenmap中
        String tokenValue= (String) tokenMap.get(tokenKey);
        if (StringUtils.isEmpty(tokenValue)){
            return false;
        }
        //token 获取成功后 删除对应的tokenmap 中的token
        tokenMap.remove(tokenKey);
        return true;
    }
}
