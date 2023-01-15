package com.hello.core.logdemo;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Service;

import com.hello.core.common.Mylogger;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LogDemoService {
  private final ObjectProvider<Mylogger> myloggerProvider;

  public void logic(String id) {
    Mylogger mylogger = myloggerProvider.getObject();
    mylogger.log("service id = " + id);
  }
}
