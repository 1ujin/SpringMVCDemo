package com.mvcdemo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class ResourceController {
    // method 默认值 GET POST 都支持
    @RequestMapping(value = "/login", method = {RequestMethod.GET, RequestMethod.POST})
    public String login(@ModelAttribute Login login, HttpSession session, Model model) {
        model.addAttribute(new Login());
        if ("paul".equals(login.getName()) && "123456".equals(login.getPassword())) {
            session.setAttribute("loggedIn", true);
            return "download";
        } else return "loginForm";
    }

    @RequestMapping(value = "/download-resource", method = {RequestMethod.GET, RequestMethod.POST})
    public String download(HttpSession session, HttpServletRequest request, HttpServletResponse response, Model model) {
        System.out.println(session.getAttribute("loggedIn"));
        if (session == null || (Boolean) session.getAttribute("loggedIn") != true) {
            model.addAttribute(new Login());
            return "loginForm";
        }
        String dir = request.getServletContext().getRealPath("/");
        Path file = Paths.get(dir, "index.jsp");
        if (Files.exists(file)) {
            response.setContentType("application/jsp");
            response.addHeader("Content-Disposition", "attachment; filename=index.jsp");
            try {
                Files.copy(file, response.getOutputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
