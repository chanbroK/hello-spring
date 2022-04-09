package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository {

    private EntityManager em;

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        em.persist(member);
        // setId 과정까지 알아서 JPA 가 알아서 함
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        // pk 를 이용한 Query는 자동 생성됨
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);
    }


    // pk 이외의 Query는 JPQL을 사용하여 쿼리를 작성해주어야 함
    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result = em.createQuery("select m from Member as m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();

        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        // 기존 SQL과는 다른 Query문을 사용한다. * 대신 테이블 자체를 필드 인자로 사용함.
        return em.createQuery("select m from Member as m", Member.class).getResultList();
    }
}
