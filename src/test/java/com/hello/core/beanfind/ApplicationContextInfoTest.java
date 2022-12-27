package com.hello.core.beanfind;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.hello.core.AppConfig;

public class ApplicationContextInfoTest {
  AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

  @Test
  @DisplayName("모든 빈 조회")
  void findAllBean() {
    String[] beanDefinitionNanes = ac.getBeanDefinitionNames();

    for (String beanDefinitionNane : beanDefinitionNanes) {
      Object bean = ac.getBean(beanDefinitionNane);
      System.out.println("name = " + beanDefinitionNane + " object = " + bean);
    }
  }

  @Test
  @DisplayName("애플리케이션 빈 조회하기")
  void findApplicationBean() {
    String[] beanDefinitionNanes = ac.getBeanDefinitionNames();

    for (String beanDefinitionName : beanDefinitionNanes) {
      BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionName);

      if (beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION) {
        Object bean = ac.getBean(beanDefinitionName);
        System.out.println("name = " + beanDefinitionName + " object = " + bean);
      }

    }
  }
}
