package cn.pys.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
class CommonErrorController implements ErrorController {

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request){
        //获取statusCode
        //Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        return "/error";

    }
    @Override
    public String getErrorPath() {
        return "/error";
    }
}