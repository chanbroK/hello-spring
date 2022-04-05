package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;

import java.util.List;
import java.util.Optional;


public class MemberService {
    private static MemberRepository memberRepo;

    public MemberService(MemberRepository memberRepo) {
        MemberService.memberRepo = memberRepo;
    }

    /**
     * 회원 가입
     *
     * @param member
     * @return id
     */
    public Long join(Member member) {
        validateDuplicateMember(member);

        memberRepo.save(member);
        return member.getId();
    }

    /**
     * 같은 이름의 중복 회원 불가능 검사
     *
     * @param member
     */
    private void validateDuplicateMember(Member member) {
        Optional<Member> result = memberRepo.findByName(member.getName());
        result.ifPresent(m -> {
            throw new IllegalStateException("이미 존재하는 이름입니다.");
        });
    }

    /**
     * 전체 회원 조회
     *
     * @return all members list
     */
    public List<Member> findMembers() {
        return memberRepo.findAll();
    }

    /**
     * 특정 회원 조회
     *
     * @param memberId
     * @return member
     */
    public Member findOne(Long memberId) {
        return memberRepo.findById(memberId).get();
    }
}
