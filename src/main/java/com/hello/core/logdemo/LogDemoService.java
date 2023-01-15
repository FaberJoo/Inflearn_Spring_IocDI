package com.hello.core.logdemo;

import org.springframework.stereotype.Service;

import com.hello.core.common.Mylogger;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LogDemoService {
  private final Mylogger mylogger;

  public void logic(String id) {
    mylogger.log("service id = " + id);
  }
}
