package com.example.restapi.implement.config;

import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Controller
@ApiIgnore
public class MyErrorController implements ErrorController {

    @RequestMapping("/error")
    @Hidden // Don't show in the Swagger doco
    @ResponseBody
    public void handleError(HttpServletRequest request, HttpServletResponse response) {
        // My handler code
    }
}