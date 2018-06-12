package com.abing.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class JSPIndexController {

    @RequestMapping("/jspIndex")
    public String jspIndex(){
        return "jspIndex";
    }
}
