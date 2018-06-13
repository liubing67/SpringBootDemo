package com.abing.intercept;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *为服务登陆，通过CAS验证（sso）,令牌（Token）
 * 判断请求，如果没有传入token 就报错（需要登陆）一般token存放在请求头中。
 */
@Component
@Slf4j
public class LoginIntercept implements HandlerInterceptor {

    /**
     * 请求之前拦截
     * @param request
     * @param response
     * @param handler
     * @return
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        String token=request.getParameter("token");
        if (StringUtils.isEmpty(token)){
            response.getWriter().println("<h1>not found token</h1>");
            return false;
        }
        //为true继续向接口下执行
        return true;
    }



}
