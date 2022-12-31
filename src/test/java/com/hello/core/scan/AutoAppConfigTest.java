package com.hello.core.scan;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.hello.core.AutoAppConfig;
import com.hello.core.member.MemberService;

public class AutoAppConfigTest {
  @Test
  @DisplayName("자동 컴포넌트 스캔")
  void basicScan() {
    ApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class);

    MemberService memberService = ac.getBean(MemberService.class);
    assertThat(memberService).isInstanceOf(MemberService.class);
  }
}
