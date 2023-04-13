package study.example.order;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import study.example.annotation.MainDiscountPolicy;
import study.example.discount.DiscountPolicy;
import study.example.discount.FixDiscountPolicy;
import study.example.member.Member;
import study.example.member.MemberRepository;
import study.example.member.MemoryMemberRepository;
@Component
//롬복 라이브러리가 제공하는 @RequiredArgsConstructor 기능을 사용하면 final이 붙은 필드를 모아서
//생성자를 자동으로 만들어준다. (다음 코드에는 보이지 않지만 실제 호출 가능하다.)
//@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{

    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    // 생성자 주입
    // 생성자를 쓰자
    // 대부분의 의존관계 주입은 한번 일어나면 애플리케이션 종료시점까지 의존관계를 변경할 일이 없다. 오히려
    // 대부분의 의존관계는 애플리케이션 종료 전까지 변하면 안된다.(불변해야 한다.)
    // 수정자 주입을 사용하면, setXxx 메서드를 public으로 열어두어야 한다.
    // 누군가 실수로 변경할 수 도 있고, 변경하면 안되는 메서드를 열어두는 것은 좋은 설계 방법이 아니다.
    // 생성자 주입은 객체를 생성할 때 딱 1번만 호출되므로 이후에 호출되는 일이 없다. 따라서 불변하게 설계할
    // 수 있다.
    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository,@MainDiscountPolicy DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    // 수정자 주입
    // 선택 변경 가능성이 있는 의존관계에 사용하지만 빈도가 많지 않다.
//    @Autowired
//    public void setMemberRepository(MemberRepository memberRepository) {
//        this.memberRepository = memberRepository;
//    }
//    @Autowired
//    public void setDiscountPolicy(DiscountPolicy discountPolicy) {
//        this.discountPolicy = discountPolicy;
//    }

    // 필드주입
    // 코드는 간결하지만 외부에서 변경이 불가능해 테스트 하기 힘듬
    // DI 프레임워크가 없으면 아무것도 할 수 없다.
//    @Autowired
//    private MemberRepository memberRepository;
//    @Autowired
//    private DiscountPolicy discountPolicy;


    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);
        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    //test
    public MemberRepository getMemberRepository() {
        return this.memberRepository;
    }
}