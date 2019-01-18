package com.abing.layui.controller;


import com.abing.layui.entity.User;
import com.abing.layui.service.CoreUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController {


    @Autowired
    CorePlatformService platformService;

    @Autowired
    CoreUserService userService;

    @Autowired
    HttpRequestLocal httpRequestLocal;

    @RequestMapping("/")
    public ModelAndView login() {
        ModelAndView view = new ModelAndView("/login.html");
        return view;
    }

    @PostMapping("/login")
    public ModelAndView login(String code, String password) {
        UserLoginInfo info = userService.login(code, password);
        if (info == null) {
            throw new PlatformException("用户名密码错");
        }
        CoreUser user = info.getUser();
        CoreOrg currentOrg = info.getOrgs().get(0);
        for (CoreOrg org : info.getOrgs()) {
            if (org.getId() == user.getOrgId()) {
                currentOrg = org;
                break;
            }
        }

        info.setCurrentOrg(currentOrg);
        // 记录登录信息到session
        this.platformService.setLoginUser(info.getUser(), info.getCurrentOrg(), info.getOrgs());
        ModelAndView view = new ModelAndView("redirect:/index.do");
        return view;
    }

}
