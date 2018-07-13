package com.springboot.test;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
//图片防盗链
@WebFilter(filterName ="imgFilter",urlPatterns = "/imgs/")
public class ImgFilter implements Filter {
    @Value("domain.name")
    private String domainName;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //1.获取请求头中的来源字段
        HttpServletRequest req= (HttpServletRequest) servletRequest;
        String referer=req.getHeader("Referer");
        if (StringUtils.isEmpty(referer)){
            servletRequest.getRequestDispatcher("/imgs/error.jpg").forward(req,servletResponse);
            return;
        }
        //2、判断请求头中的域名是否和限制一致
       String domainUrl= getDomain(referer);
        if (!domainUrl.equals(domainName)){
            servletRequest.getRequestDispatcher("/imgs/error.jpg").forward(req,servletResponse);
            return;
        }
        //直接放行图片
        filterChain.doFilter(req,servletResponse);
    }

    /**
     * 获取url对应的域名
     * @param url
     * @return
     */
    public String getDomain(String url){
        String result="";
        int j=0,startIndex=0,endIndex=0;
        for (int i=0;i<url.length();i++){
            if (url.charAt(i)=='/'){
                j++;
                if (j==2)
                    startIndex=i;
                else if (j==3)
                    endIndex=i;
            }
        }
        result=url.substring(startIndex+1,endIndex);
        return result;
    }
    @Override
    public void destroy() {

    }
}
