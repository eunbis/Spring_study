package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderServiceImpl implements OrderService{

    @Autowired private MemberRepository memberRepository;
    @Autowired private DiscountPolicy discountPolicy; // DIP를 위반하지 않도록 설정, final을 붙이면 무조건 값을 할당해야 하므로, final을 지운다.

    //NullPointerException 제거 위해 setter 생성
    public void setMemberRepository(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public void setDiscountPolicy(DiscountPolicy discountPolicy) {
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        //1. 회원 정보 조회
        Member member = memberRepository.findById(memberId); //NullPointerException 발생
        //2. 할인 여부 조회
        int discountPrice = discountPolicy.discount(member, itemPrice);
        //Order 서비스는 할인 정책을 아예 모르므로, discountPolicy에 넘겨버림 (단일 책임의 원칙 설계 good)
        //DIP 위반하지 않도록 인터페이스에만 의존하도록 하면, 아무것도 할당되어있지 않으므로 이 코드를 실행했을 때 NullPointException이 발생함

        //주문 정보 반환
        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    //테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
