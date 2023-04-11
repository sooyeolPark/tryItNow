package study.example.discount;

import study.example.member.Member;


public interface DiscountPolicy {
    int discount(Member member, int price);
}
