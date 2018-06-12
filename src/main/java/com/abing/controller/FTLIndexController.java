package com.abing.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
public class FTLIndexController {

    @RequestMapping("/ftlIndex")
    public String ftlIndex(Map<String,String> map){
        map.put("name","阿冰");
        map.put("age","20");
        map.put("sex","0");
        return "ftlIndex";
    }
}
