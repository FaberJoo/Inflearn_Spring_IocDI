package com.hello.core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.hello.core.AppConfig;
import com.hello.core.member.MemberRepository;
import com.hello.core.member.MemberServiceImpl;
import com.hello.core.order.OrderServiceImpl;

public class ConfigurationSingletonTest {
  @Test
  @DisplayName("AppConfig Bean 중복 검사")
  void configurationTest() {
    ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
    OrderServiceImpl orderService = ac.getBean("orderService", OrderServiceImpl.class);
    MemberRepository memberRepository = ac.getBean("memberRepository", MemberRepository.class);

    System.out.println("memberService -> memberRepository = " + memberService.getMemberRepository());
    System.out.println("orderService -> orderRepository = " + orderService.getMemberRepository());
    System.out.println("memberRepository = " + memberRepository);

    Assertions.assertThat(memberService.getMemberRepository()).isSameAs(memberRepository);
    Assertions.assertThat(orderService.getMemberRepository()).isSameAs(memberRepository);
  }

  @Test
  @DisplayName("AppConfig 바이트 조작 확인")
  void configurationDeep() {
    ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    AppConfig bean = ac.getBean(AppConfig.class);

    System.out.println("bean = " + bean.getClass());
  }
}
