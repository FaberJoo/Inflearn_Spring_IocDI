package com.hello.core.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hello.core.common.Mylogger;
import com.hello.core.logdemo.LogDemoService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class LogDemoController {
  private final LogDemoService logDemoService;
  private final ObjectProvider<Mylogger> myloggerProvider;

  @RequestMapping("log-demo")
  @ResponseBody
  public String logDemo(HttpServletRequest request) {
    String requestURL = request.getRequestURL().toString();
    Mylogger mylogger = myloggerProvider.getObject();
    mylogger.setRequestURL(requestURL);

    mylogger.log("controller test");
    logDemoService.logic("testId");
    return "OK";
  }
}
