package com.hello.core.lifeCycle;

import org.junit.jupiter.api.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class BeanLifeCycleTest {
  @Test
  void lifeCycleTest() {
    ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(LifeCycleConfig.class);
    NetworkClient client = ac.getBean(NetworkClient.class);
    System.out.println("생성 후");
    ac.close();
    System.out.println("종료 후");
  }

  @Configuration
  static class LifeCycleConfig {
    @Bean(initMethod = "init", destroyMethod = "close")
    public NetworkClient networkClient() {
      NetworkClient networkClient = new NetworkClient();
      System.out.println("설정 전");
      networkClient.setUrl("http://hello-spring.dev");
      System.out.println("설정 후");
      return networkClient;
    }
  }
}
