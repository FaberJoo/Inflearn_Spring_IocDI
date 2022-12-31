package com.hello.core.scan;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.ComponentScan.Filter;

import com.hello.core.scan.filter.BeanA;
import com.hello.core.scan.filter.BeanB;
import com.hello.core.scan.filter.MyExcludeComponent;
import com.hello.core.scan.filter.MyIncludeComponent;

public class ComponentFilterAppConfigTest {
  @Test
  @DisplayName("컴포넌트 스캔 필터 적용")
  void filterScan() {
    ApplicationContext ac = new AnnotationConfigApplicationContext(ComponentFilterAppConfig.class);

    BeanA beanA = ac.getBean("beanA", BeanA.class);
    assertThat(beanA).isNotNull();

    Assertions.assertThrows(NoSuchBeanDefinitionException.class,
        () -> ac.getBean("beanB", BeanB.class));
  }

  @Configuration
  @ComponentScan(includeFilters = @Filter(type = FilterType.ANNOTATION, classes = MyIncludeComponent.class), excludeFilters = @Filter(type = FilterType.ANNOTATION, classes = MyExcludeComponent.class))
  static class ComponentFilterAppConfig {
  }
}
