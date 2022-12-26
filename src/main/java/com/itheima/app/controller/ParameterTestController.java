package com.itheima.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sun.plugin2.applet.AWTAppletSecurityManager;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ParameterTestController {
    /*
     * @Author GhostGalaxy
     * @Description 如果通过 @PathVariable指定一个Map<String, String>类型的参数，这个参数会将路径上ant风格的所有参数封装在这个map中
     * 如果通过@RequestParam 指定一个Map<String, String>类型的参数，这个参数会将路径上链接携带的所有参数封装在这个map中
     * @CookieValue("_ga") Cookie cookie 将整个cookie对象获取到
     * @Date 15:07:50 2022/12/22
     * @Param
     * @return
     **/
    @ResponseBody
    @GetMapping("/car/{id}/owner/{username}")
    public Map<String,Object> getParameter(@PathVariable("id") Integer id,
                                           @PathVariable("username") String userName,
                                           @PathVariable Map<String, String> paramMap,
                                           @RequestHeader("User-Agent") String UserAgent,
                                           @RequestHeader Map<String,String> headers,
                                           @RequestParam("age") Integer age, @RequestParam("hobbies") String[] hobbies,
                                           @RequestParam Map<String,String> allParameter,
                                           @CookieValue("_ga") String _ga, @CookieValue("_ga") Cookie cookie){
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("id",id);
        map.put("userName", userName);
        map.put("paramMap",paramMap);
        map.put("User-Agent",UserAgent);
        map.put("headers",headers);
        map.put("age",age);
        map.put("hobbies",hobbies);
        map.put("allParameter",allParameter);
        map.put("_ga",_ga);
        map.put("cookie",cookie);
        System.out.println(cookie.getName()+"========"+cookie.getValue());
        return map;
    }

    /*
     * @Author GhostGalaxy
     * @Description 测试 @RequestBody 接收到这个参数后返回到页面 或者转发到其它页面
     * c测试结果为     "content": "username=zhangsan&password=123456"
     * @Date 15:28:28 2022/12/22
     * @Param
     * @return
     **/
    @ResponseBody
    @PostMapping("/save")
    public Map<String,String> postMethod(@RequestBody String content){
         Map<String,String> map = new HashMap<String,String>();
         map.put("content",content);
         return map;
    }
    /*
     * @Author GhostGalaxy
     * @Description 模拟将数据放在请求域中返回给页面
     * @Date 15:40:16 2022/12/22
     * @Param [content]
     * @return java.util.Map<java.lang.String,java.lang.String>
     **/
    @PostMapping("/save1")
    public String postMethod1(@RequestBody String content,HttpServletRequest request){
        request.setAttribute("content",content);
        return "forward:/success";
    }
    /*
     * @Author GhostGalaxy
     * @Description 通过@RequestAttribute获取请求域中的数据  第二种方式 获取到原生的HttpServletRequest request 然后通过request.getAttribute("content")
     * 测试结果为 ：  通过原生的HttpServletRequest获取请求域中的数据username=user&password=9e83f171-17b1-4a28-881d-79e8be25db61
                    通过@RequestAttribute('content')获取请求域中的数据username=user&password=9e83f171-17b1-4a28-881d-79e8be25db61
     * @Date 15:43:53 2022/12/22
     * @Param [content]
     * @return java.lang.String
     **/
    @ResponseBody
    @PostMapping("/success")
    public String goToPage(@RequestAttribute(value = "content",required = false) String content,HttpServletRequest request){
        String content1 = (String) request.getAttribute("content");
        System.out.println("通过原生的HttpServletRequest获取请求域中的数据"+content1);
        System.out.println("通过@RequestAttribute('content')获取请求域中的数据"+content);
        return content;
    }
    /*
     * @Author GhostGalaxy
     * @Description 测试请求路径为  /cars/sale;min=10;brand=BWM,BYD,BY
     * springboot默认不开启矩阵变量功能  路径的真实值为sale
     * @Date 16:22:21 2022/12/22
     * @Param [min, brand]
     * @return java.util.Map<java.lang.String,java.lang.Object>
     **/
    @ResponseBody
    @GetMapping("/cars/{path}")
    public Map<String,Object> getMatrixVariableParam(@MatrixVariable("min") Integer min, @MatrixVariable("brand") List<String> brand,
                                                     @PathVariable("path") String path){
           Map<String,Object> map = new HashMap<String,Object>();
           map.put("min",min);
           map.put("brand",brand);
           System.out.println("路径的真实值为"+path);
           return map;
    }
    /*
     * @Author GhostGalaxy
     * @Description //TODO 请求路径为 /boss/1;age=20/2;age=10  需要通过一个pathVar属性做区分
     * @Date 17:06:45 2022/12/22
     * @Param [bossId, empId, path]
     * @return java.util.Map<java.lang.String,java.lang.Object>
     **/
    @ResponseBody
    @GetMapping("/boss/{bossId}/{empId}")
    public Map<String,Object> getMatrixVariableParam02(@MatrixVariable(value = "age",pathVar = "bossId") Integer bossAge, @MatrixVariable(value = "age",pathVar = "empId") Integer empAge,
                                                     @PathVariable("bossId") String path){
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("bossAge",bossAge);
        map.put("empAge",empAge);
        System.out.println("路径的真实值为"+path);
        return map;
    }
    /*
     * @Author GhostGalaxy
     * @Description //复杂参数的原理 给map，model中放数据相当于放入到请求域中
     *
     * @Date 15:07:59 2022/12/23
     * @Param [param1, param2, request, response]
     * @return java.lang.String
     **/
    @GetMapping( "/param/test")
    public String complexParam(Map<String,Object> param1, Model param2, HttpServletRequest request, HttpServletResponse response){
        param1.put("hello","world");
        param2.addAttribute("spring","springboot 2");
        request.setAttribute("world","hello");
        Cookie cookie = new Cookie("c","v");

        response.addCookie(cookie);
        return "forward:/page";
    }

    @ResponseBody
    @GetMapping("/page")
    public Map<String,Object> gotoPage(HttpServletRequest request){
        Object hello = request.getAttribute("hello");
        Object spring = request.getAttribute("spring");
        Object world = request.getAttribute("world");
        Map<String,Object> map = new HashMap<>();
        map.put("hello",hello);
        map.put("spring",spring);
        map.put("world",world);
        return map;
    }
}
