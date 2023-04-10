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

//@Configuration 어노테이션을 적용하지 않으면 @Bean은 등록되어 사용 가능하지만 Singleton이 깨지게 된다.
@Configuration
public class AppConfig {

    // @Bean memberService -> new MemoryMemberRepository()
    // @Bean orderService -> new MemoryMemberRepository()

    //예상
    // call AppConfig.memberService
    // call AppConfig.memberRepository
    // call AppConfig.orderService
    // call AppConfig.memberRepository
    // call AppConfig.memberRepository

    //실제 -> spring이 singleton을 보장해주는 걸 알 수 있음
    // call AppConfig.memberService
    // call AppConfig.memberRepository
    // call AppConfig.orderService
    @Bean
    public MemberService memberService() {
        System.out.println("call AppConfig.memberService");
        return new MemberServiceImpl(memberRepository());
    }
    @Bean
    public OrderService orderService() {
        System.out.println("call AppConfig.orderService");
        return new OrderServiceImpl(
                memberRepository(),
                discountPolicy());
    }
    @Bean
    public MemberRepository memberRepository() {
        System.out.println("call AppConfig.memberRepository");

        return new MemoryMemberRepository();
    }
    @Bean
    public DiscountPolicy discountPolicy() {
//        return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }
}

