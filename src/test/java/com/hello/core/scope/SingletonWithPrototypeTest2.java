package com.hello.core.scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import com.hello.core.scope.PrototypeTest.PrototypeBean;

public class SingletonWithPrototypeTest2 {
  @Test
  void singletonClientPrototype() {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ClientBean.class,
        PrototypeBean.class);

    ClientBean clientBean1 = ac.getBean(ClientBean.class);
    int count1 = clientBean1.logic();
    assertThat(count1).isEqualTo(1);

    ClientBean clientBean2 = ac.getBean(ClientBean.class);
    int count2 = clientBean2.logic();
    assertThat(count2).isEqualTo(2);
  }

  @Scope("singleton")
  static class ClientBean {
    private final PrototypeBean prototypeBean;

    @Autowired
    public ClientBean(PrototypeBean prototypeBean) {
      this.prototypeBean = prototypeBean;
    }

    public int logic() {
      prototypeBean.addCount();
      int count = prototypeBean.getCount();
      return count;
    }
  }

  @Scope("prototype")
  static class PrototypeBean {
    private int coint = 0;

    public int getCount() {
      return coint;
    }

    public void addCount() {
      this.coint++;
    }

    @PostConstruct
    public void init() {
      System.out.println("PrototypeBean.init " + this);
    }

    @PreDestroy
    public void destory() {
      System.out.println("PrototypeBean.destroy");
    }
  }
}
