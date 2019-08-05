package com.sunpeifu.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
@RestController
public class HttpController {
    @GetMapping("testHttpClientUtilGet")
    public String testHttpClientUtilGet(HttpServletRequest request){
        String name=request.getParameter("name");
        String age = request.getParameter("age");
        StringBuffer sb = new StringBuffer();

        return sb.append(name).append(" ").append(age).toString();
    }
    @PostMapping("testHttpClientUtilPost")
    public String testHttpClientUtilPoset(HttpServletRequest request){
        String name=request.getParameter("name");
        String age = request.getParameter("age");
        return "结果是:"+name+"  "+age;
    }
    @PostMapping("testHttpClientUtilPostJson")
    public String testHttpClientUtilPoset(@RequestBody User user){
        System.out.println(user.toString());
        return user.toString();
    }

    public static void main(String[] args) {
        String beanClassName="com.sunpeifu.demo.class";
        String s = beanClassName.substring(beanClassName.lastIndexOf(".") + 1);
        System.out.println(s);
    }
    public static String lowerFristCase(String str) {
        char[] chars = str.toCharArray();
        chars[0] += 32;
        return String.valueOf(chars);
    }
}
