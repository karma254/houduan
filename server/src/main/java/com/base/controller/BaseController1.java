package com.base.controller;

import com.base.util.JsonResult;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class BaseController1 {
    protected HttpServletRequest request;
    protected HttpServletResponse response;
    protected HttpSession session;

    @ModelAttribute
    public void setReqAndRes(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
        this.session = request.getSession();
    }

    protected JsonResult renderSuccess() {
        return new JsonResult(200, "操作成功", null);
    }

    protected JsonResult renderSuccess(Object data) {
        return new JsonResult(200, "操作成功", data);
    }

    protected JsonResult renderSuccess(String msg, Object data) {
        return new JsonResult(200, msg, data);
    }

    protected JsonResult renderError(String msg) {
        return new JsonResult(500, msg, null);
    }

    protected JsonResult renderError(int code, String msg) {
        return new JsonResult(code, msg, null);
    }
}