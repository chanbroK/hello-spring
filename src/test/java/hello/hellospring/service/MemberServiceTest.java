package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach
    public void beforeEach() {
        // DI(Dependency Injection)
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach
    public void afterEach() {
        memberRepository.clearStore();
    }

    @Test
    void 회원가입() {
        // given
        Member member = new Member();
        member.setName("hello");

        //when
        Long saveId = memberService.join(member);

        //then
        Member findMember = memberService.findOne(saveId);
        assertThat(member.getName()).isEqualTo(findMember.getName());

    }

    @Test
    void 중복회원예외처리() {
        // given
        Member member1 = new Member();
        member1.setName("hello");
        Member member2 = new Member();
        member2.setName("hello");

        // when
        memberService.join(member1);

        // try catch 이용하여 예외처리 테스트
//        try {
//            memberService.join(member2);
//            fail("예외가 발생해야 합니다.");
//        } catch (IllegalStateException e) {
//             // then
//            assertThat(e.getMessage()).isEqualTo("이미 존재하는 이름입니다.");
//        }

        // assertThrows 이용하여 예외처리 테스트
        //then
        IllegalStateException exception = Assertions.assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(exception.getMessage()).isEqualTo("이미 존재하는 이름입니다.");
    }

    @Test
    void findMembers() {

    }

    @Test
    void findOne() {

    }
}
