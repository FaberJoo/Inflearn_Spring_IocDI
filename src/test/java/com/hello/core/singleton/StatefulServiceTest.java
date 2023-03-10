package com.hello.core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class StatefulServiceTest {
  @Test
  @DisplayName("상태를 유지할 경우 발생하는 문제")
  void statefulServiceSingleton() {
    ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);

    StatefulService statefulService1 = ac.getBean("statefulService", StatefulService.class);
    StatefulService statefulService2 = ac.getBean("statefulService", StatefulService.class);

    statefulService1.order("UserA", 10000);
    statefulService2.order("UserB", 20000);
    System.out.println("price = " + statefulService1.getPrice());

    Assertions.assertThat(statefulService1.getPrice()).isEqualTo(20000);
  }

  @Configuration
  static class TestConfig {

    @Bean
    public StatefulService statefulService() {
      return new StatefulService();
    }
  }
}
