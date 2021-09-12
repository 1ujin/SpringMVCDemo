package com.mvcdemo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Controller
public class BidController {
    // 在名称为“command”的ModelAndView对象中传递一个空的Bid对象，因为spring框架需要一个名称的“command”的对象，如果你在JSP文件中使用<form:form>标签。
    @RequestMapping(value = "/bid", method = RequestMethod.GET)
    public ModelAndView bid() {
        Bid bid = new Bid();
        bid.setNo(100);
        // 此处对象的名称必须为command
        return new ModelAndView("bid", "bid", bid); // 方便的构造函数来取一个模型对象
        // return "bid";
    }

    // ModelAttribute注解位置可以在public关键字后
    // 利用ModelAttribute实例化一个对象
    // https://www.jianshu.com/p/cf9acf314a4c?utm_source=oschina-app
    // 如果不设置value则value根据持有的对象类型前置,容器的类型后置,首字母小写暴露为stringList
    public @ModelAttribute(value = "checkItems") List<String> getCheckItems() {
        List<String> checkItems = new ArrayList<>();
        checkItems.add("Spring MVC");
        checkItems.add("Spring Boot");
        checkItems.add("Struts 2");
        checkItems.add("Apache Hadoop");
        return checkItems;
    }

    @RequestMapping(value = "/addBid", method = RequestMethod.POST)
    @ExceptionHandler({SpringException.class})
    // ModelAttribute注解的方法参数，表示该参数会从Model中查找。如果该参数不在Model中，此参数会先执行初始化然后添加到Model里；如果参数存在Model里，此参数字段会填充所有匹配其名称的请求参数。
    // ModelAttribute将注解的bid对象即请求参数，赋给了model中的属性“mb”，bid -> model.mb
    // 实际上，不做此注解也能拿到bid对象
    // 可以是Model或者ModelMap类型，Model类继承ModelMap类；RequestBody注释将传递的参数赋给此处的body
    // BindingResult参数必须紧跟在其所绑定的验证对象后面，可以用其验证错误
    public String addBid(@ModelAttribute("mb") Bid bid, BindingResult bindingResult, Model model, BindingResult bindingResult1, @RequestBody String body, BindingResult bindingResult2) {
        // model 中的 mb 属性保存的对象和 bid 对象地址相同
        System.out.println(model.asMap().get("mb") == bid);
        System.out.println(model.asMap().get("mb"));
        if (bid.getNo() <= 0) {
            throw new SpringException("No. cannot be null!");
        } else {
            model.addAttribute("no", bid.getNo());
        }
        model.addAttribute("title", bid.getTitle());
        model.addAttribute("url", bid.getUrl());
        model.addAttribute("date", bid.getDate());
        model.addAttribute("source", bid.getSource());
        model.addAttribute("checks", bid.getChecks());
        model.addAttribute("哈哈哈", "哈哈哈");
        System.out.println("哈哈哈");
        System.out.println(bid.getTitle());
        // 输出: _method=PUT&no=100&title=1&url=2&date=3&source=4&checks=Spring+MVC&checks=Spring+Boot&_checks=on
        System.out.println("RequestBody注释的参数为: " + body);
        System.out.println("验证bid: " + bindingResult.getModel());
        System.out.println(model);
        System.out.println("验证Model: " + bindingResult1.getModel());
        System.out.println(body);
        System.out.println("验证RequestBody: " + bindingResult2.getModel());
        return "result";
    }

    // 有多少次数据绑定，就会调用多少次initBinder所注解的方法
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        System.out.println("This is a binder.");
    }
}
