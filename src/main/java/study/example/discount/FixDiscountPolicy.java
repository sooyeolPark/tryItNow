package study.example.discount;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import study.example.annotation.MainDiscountPolicy;
import study.example.member.Grade;
import study.example.member.Member;

@Component
//@Qualifier("mainDiscountPolicy")
//@Qualifier 로 주입할 때 @Qualifier("mainDiscountPolicy") 를 못찾으면 어떻게 될까? 그러면
//mainDiscountPolicy라는 이름의 스프링 빈을 추가로 찾는다. 하지만 경험상 @Qualifier 는
//@Qualifier 를 찾는 용도로만 사용하는게 명확하고 좋다.

//@Primary
//@Primary 는 우선순위를 정하는 방법이다. @Autowired 시에 여러 빈이 매칭되면 @Primary 가 우선권을 가진다.
@MainDiscountPolicy
public class FixDiscountPolicy implements DiscountPolicy{

    private int discountFixAmount = 1000;
    @Override
    public int discount(Member member, int price) {
        if (member.getGrade() == Grade.VIP) {
            return discountFixAmount;
        } else  {
            return 0;
        }
    }
}
