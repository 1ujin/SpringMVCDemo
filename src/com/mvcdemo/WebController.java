package com.mvcdemo;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@Controller
public class WebController {

    @GetMapping(value = "/mv/{userId}")
    public void matrixVariableMethod(@MatrixVariable(value = "name", pathVar = "userId") String name) {
        System.out.println(name);
    }

    @RequestMapping(value = "index", method = RequestMethod.GET)
    public String index(HttpServletRequest request, HttpServletResponse response) {
        Enumeration Attributes = request.getServletContext().getAttributeNames();
        while (Attributes.hasMoreElements())
            System.out.println(Attributes.nextElement());
        ApplicationContext ac = (ApplicationContext) request.getServletContext().getAttribute("org.springframework.web.context.WebApplicationContext.ROOT");
        System.out.println("root context 中: " + ac.getBean("str1"));
        // System.out.println("错误，抛异常，不能获得 child context 中: " + ac.getBean("str2"));
        ac = (ApplicationContext) request.getServletContext().getAttribute("org.springframework.web.servlet.FrameworkServlet.CONTEXT.SpringMVCDemo");
        System.out.println("child context 中: " + ac.getBean("str1"));
        return "index"; // 此处查找view下的视图文件index.jsp，在SpringMVCDemo-servlet.xml中名为"prefix"的标签中设置
    }

    @RequestMapping(path = "/redirect", method = RequestMethod.GET)
    public String redirect() {
        // 冒号后跟斜杠相对于该项目，否则相当于当前路径追加
        // return "forward:/bid";
        return "redirect:/bid";
    }

    @RequestMapping(path = "/finalPage/{ID}/{no}", method = RequestMethod.GET, params = "method=plus", headers = "host=localhost:8080")
    // 筛选请求参数和请求头
    // URI模板中的变量名与注释中的变量名和视图中的表达式变量名一致，默认情况下为方法中的参数名id -> "ID"，no -> "no"；RequestParam注释标记参数method的值，此处为plus，也可以使用一个POJO来包装这个或这些参数，这个POJO本身没有要求额外的注解，但是POJO本身必须包含和请求参数完全匹配的字段，标准的setter/getter，和一个无参的构造器 https://www.jianshu.com/p/b4c8d8de4dad
    // CookieValue可以获取cookie中键为<name>的值，如果没有赋值则默认为所注解的参数的名字
    public String finalPage(HttpServletRequest request, @RequestAttribute(value = "request中自行添加的属性", required = false) Long num, @PathVariable(name = "ID") int id, @PathVariable int no, @PathVariable Map<String, String> map, @RequestParam(value = "method", required = false, defaultValue = "plus") String param, @CookieValue String JSESSIONID, @RequestHeader Map<String, String> headers) {
        System.out.println("request中自行添加的属性: " + num);
        System.out.println("request中自行添加的属性myAttribute: " + request.getAttribute("request中自行添加的属性myAttribute"));
        // 在request中添加属性
        request.setAttribute("request中自行添加的属性myAttribute", "myValue");
        System.out.println("RequestParam注解的值: " + param);
        // Jsp得不到变量map
        System.out.println("注解cookie中JSESSIONID的值: " + JSESSIONID);
        // System.out.println("注解请求头中Host属性的值: " + host);
        for (String header : headers.keySet()) {
            System.out.println("headers中" + header + ": " + headers.get(header));
        }
        for (String key : map.keySet()) {
            System.out.println("map中" + key + ": " + map.get(key));
        }
        return "final";
    }

    @RequestMapping(value = "/staticPage", method = RequestMethod.GET)
    public String staticPage() {
        // 冒号后跟斜杠相对于该项目，否则相当于当前路径追加
        return "redirect:/static/final.htm";
    }

    // 有多少次数据绑定，就会调用多少次initBinder所注解的方法
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        System.out.println("This is a binder.");
    }
}
