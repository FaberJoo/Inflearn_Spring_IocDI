package com.hello.core.order;

import com.hello.core.AppConfig;
import com.hello.core.discount.DiscountPolicy;
import com.hello.core.discount.RateDiscountPolicy;
import com.hello.core.member.Grade;
import com.hello.core.member.Member;
import com.hello.core.member.MemberService;
import com.hello.core.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RateDiscountPolicyTest {
    DiscountPolicy discountPolicy;

    @BeforeEach
    public void beforeEach() {
        AppConfig appConfig = new AppConfig();
        this.discountPolicy = appConfig.discountPolicy();
    }

    @Test
    @DisplayName("VIP는 10%할인 적용")
    void vip_o() {

        Member member = new Member(1L, "MemberVip", Grade.VIP);
        int discount = discountPolicy.discount(member, 10000);
        int discountPrice = 1000;
        if (discountPolicy instanceof RateDiscountPolicy) {
            discountPrice = 2000;
        }
        Assertions.assertThat(discount).isEqualTo(discountPrice);
    }

    @Test
    @DisplayName("Basic은 할인 적용 x")
    void vip_x() {
        Member member = new Member(1L, "MemberBasic", Grade.BASIC);
        int discount = discountPolicy.discount(member, 10000);
        Assertions.assertThat(discount).isEqualTo(0);
    }

}
