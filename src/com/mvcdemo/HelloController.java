package com.mvcdemo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
// 匹配url的路径
@RequestMapping(value = {"/hello", "/hello/{word}"})
public class HelloController {
    @RequestMapping(method = RequestMethod.GET)
    // PathVariable 如果 required = false，而你没有传这个参数，那么它会去找这个参数去掉之后的替代 url (/hello)，如果发现有替代的 url，就可以处理这个请求，如果没有找到，就抛出异常不去处理
    public String printHello(ModelMap model, @PathVariable(required = false) String word) {
        model.addAttribute("message", "Hello Spring MVC Framework!" + word);
        // 返回给能够接收到的 web.xml 中配置的 DispatcherServlet，再发送给对应的 beans.xml 中配置的试图解析器（ViewResolver），返回的名称前加上 prefix，后加上 suffix，形成视图文件的路径
        return "hello";
    }
}
