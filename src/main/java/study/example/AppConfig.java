package study.example;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import study.example.discount.DiscountPolicy;
import study.example.discount.FixDiscountPolicy;
import study.example.discount.RateDiscountPolicy;
import study.example.member.MemberRepository;
import study.example.member.MemberService;
import study.example.member.MemberServiceImpl;
import study.example.member.MemoryMemberRepository;
import study.example.order.OrderService;
import study.example.order.OrderServiceImpl;

@Configuration
public class AppConfig {
    @Bean
    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }
    @Bean
    public OrderService orderService() {
        return new OrderServiceImpl(
                memberRepository(),
                discountPolicy());
    }
    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
    @Bean
    public DiscountPolicy discountPolicy() {
//        return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }
}

