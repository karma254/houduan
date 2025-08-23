package com.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import util.Request;

@Controller
public class PaymentController extends BaseController{
    /**
     * 处理支付
     * @return String
     */
    @RequestMapping("/payment")
    public String payment()
    {
        String referer = Request.get("referer").equals("") ? "sy.do" : Request.get("referer");
        return showSuccess("支付成功",referer);
    }
}
