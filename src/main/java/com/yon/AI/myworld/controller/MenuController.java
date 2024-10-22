package com.yon.AI.myworld.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yon.AI.myworld.service.MenuService;

@RestController
public class MenuController {

    @Autowired
    private MenuService menuService;

    @GetMapping(path = "/MenuHome.htm")
    public String getMenuHome() {
        System.out.println("Enter Controller :::");
        String html = "";
        
        List<Map<String, Object>> menuList = menuService.getMenuList();
        
        Map<String, Object> menuDesign = menuService.getMenuDesign(1);
        
        List<String> obj = new ArrayList<>();

  
        if (menuDesign != null && menuDesign.containsKey("param")) {
           
            for (Map<String, Object> menu : menuList) {
            	 String param = (String) menuDesign.get("param");
                if (param != null && !param.isEmpty() && param.contains("{OPTONS}")) {
                	param= param.replace("{OPTONS}", (String) menu.get("menuName"));
                }
                if (param != null && !param.isEmpty() && param.contains("{URL}")) {
                	param= param.replace("{URL}", (String) menu.get("menuUrl"));
                }
                obj.add(param);
            }
        }

     
        String objAsString = String.join("", obj); 
        if (menuDesign != null && menuDesign.containsKey("body")) {
            String body = (String) menuDesign.get("body");
            if (body.contains("{LIST}")) {
                body = body.replace("{LIST}", objAsString);
                menuDesign.put("body", body);
            }
        }

        if (menuDesign != null) {
            html = menuDesign.get("body") + "<br>" + menuDesign.get("style") + "<br>" + menuDesign.get("script");
        }
        
        return html;
    }
}
