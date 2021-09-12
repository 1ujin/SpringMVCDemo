package com.mvcdemo;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JsonController {
    @RequestMapping(value = "/getBid", method = RequestMethod.GET)
    public Bid getBid() {
        Bid bid = new Bid();
        bid.setNo(1);
        return bid;
    }
}
