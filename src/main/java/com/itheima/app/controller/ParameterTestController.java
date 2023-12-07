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
     * @return  请求测试路径：car/2/owner/zhangsan?age=23&hobbies=baskball,pingpong,football
     *
     *
     **/
//    测试结果为:
//    {
//        "headers": {
//        "host": "localhost:8080",
//                "connection": "keep-alive",
//                "sec-ch-ua": "\"Microsoft Edge\";v=\"119\", \"Chromium\";v=\"119\", \"Not?A_Brand\";v=\"24\"",
//                "sec-ch-ua-mobile": "?0",
//                "sec-ch-ua-platform": "\"Windows\"",
//                "upgrade-insecure-requests": "1",
//                "user-agent": "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/119.0.0.0 Safari/537.36 Edg/119.0.0.0",
//                "accept": "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7",
//                "sec-fetch-site": "none",
//                "sec-fetch-mode": "navigate",
//                "sec-fetch-user": "?1",
//                "sec-fetch-dest": "document",
//                "accept-encoding": "gzip, deflate, br",
//                "accept-language": "zh-CN,zh;q=0.9,en;q=0.8,en-GB;q=0.7,en-US;q=0.6",
//                "cookie": "_ga=GA1.1.916179505.1640834461; Idea-6ddc79dd=cc95f220-b44a-4190-81b3-8f4f5837fb5f; bftrack_sid=146876F9-9F9E-4419-800B-9B89059CEF55; bftrack_uuid=058146CF-C8FE-4EC4-B953-04492B4752F1; bftrack_previsit=1691056526530"
//    },
//        "cookie": {
//        "name": "_ga",
//                "value": "GA1.1.916179505.1640834461",
//                "version": 0,
//                "comment": null,
//                "domain": null,
//                "maxAge": -1,
//                "path": null,
//                "secure": false,
//                "httpOnly": false
//    },
//        "hobbies": [
//        "baskball",
//                "pingpong",
//                "football"
//    ],
//        "User-Agent": "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/119.0.0.0 Safari/537.36 Edg/119.0.0.0",
//            "_ga": "GA1.1.916179505.1640834461",
//            "id": 2,
//            "userName": "zhangsan",
//            "paramMap": {
//        "id": "2",
//                "username": "zhangsan"
//    },
//        "age": 23,
//            "allParameter": {
//        "age": "23",
//                "hobbies": "baskball,pingpong,football"
//    }
//    }
    @ResponseBody
    @RequestMapping("/car/{id}/owner/{username}")
    public Map<String,Object> getParameter(@PathVariable("id") Integer id,
                                           @PathVariable("username") String userName,
                                           @PathVariable Map<String, String> paramMap,
                                           @RequestHeader("User-Agent") String UserAgent,
                                           @RequestHeader Map<String,String> headers,
                                           @RequestParam("age") Integer age,
                                           @RequestParam("hobbies") String[] hobbies,
                                           @RequestParam Map<String,String> allParameter,
                                           @CookieValue("_ga") String _ga,
                                           @CookieValue("_ga") Cookie cookie){
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

    /**
     * @description: TODO
     * @author 20609
     * @date 2023/12/6 18:41
     * @version 1.0
     */

    /*
     * @Author GhostGalaxy
     * @Description 测试 @RequestBody 接收到这个参数后返回到页面 或者转发到其它页面
     * 测试结果为     "content": "username=zhangsan&password=123456"
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
     * 请求转发可能丢失request域中的数据
     * @Description 通过@RequestAttribute获取请求域中的数据  第二种方式 获取到原生的HttpServletRequest request 然后通过request.getAttribute("content")
     * 测试结果为 ：  通过原生的HttpServletRequest获取请求域中的数据username=user&password=9e83f171-17b1-4a28-881d-79e8be25db61
                    通过@RequestAttribute('content')获取请求域中的数据username=user&password=9e83f171-17b1-4a28-881d-79e8be25db61
       特别注意 RequestAttribute并不支持给方法设定一个map类型的参数获取所有的request中的数据
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
    public Map<String,Object> getMatrixVariableParam02(@MatrixVariable(value = "age",pathVar = "bossId") Integer bossAge,
                                                       @MatrixVariable(value = "age",pathVar = "empId") Integer empAge,
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
