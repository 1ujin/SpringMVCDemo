package com.mvcdemo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class VideoController {
    @RequestMapping(value = "/mostViewed", method = RequestMethod.GET)
    public String getMostViewed(HttpServletRequest request, HttpServletResponse response) {
        Integer id = (Integer) request.getAttribute("id");
        if (id == null) {
            response.setStatus(500);
        } else if (id == 1) {
            request.setAttribute("viewed", 100);
        } else if (id == 2) {
            request.setAttribute("viewed", 200);
        }
        return null;
    }
}
