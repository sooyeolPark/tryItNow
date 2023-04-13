package study.example.scan;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import study.example.AutoAppConfig;
import study.example.member.MemberRepository;
import study.example.member.MemberService;
import study.example.order.OrderServiceImpl;

public class AutoAppConfigTest {

    @Test
    void basicScan() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class);

        MemberService memberService = ac.getBean(MemberService.class);
        Assertions.assertThat(memberService).isInstanceOf(MemberService.class);

        OrderServiceImpl bean = ac.getBean(OrderServiceImpl.class);
        MemberRepository memberRepository = bean.getMemberRepository();
        System.out.println("memberRepository = " + memberRepository);
    }
}
