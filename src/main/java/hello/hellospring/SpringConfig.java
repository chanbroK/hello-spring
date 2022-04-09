package hello.hellospring;


import hello.hellospring.repository.JpaMemberRepository;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    private DataSource dataSource;
    private EntityManager em;

    @Autowired
    public SpringConfig(DataSource dataSource, EntityManager em) {
        // DI from Spring boot
        this.dataSource = dataSource;
        this.em = em;
    }


    @Bean
    public MemberService memberService() {
        // DI
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        // use Memory
//        return new MemoryMemberRepository();
        // use Pure JDBC
//        return new JdbcMemberRepository(dataSource);
        // use JDBCTemplate
//        return new JdbcTemplateMemberRepository(dataSource);
        // use JPA
        return new JpaMemberRepository(em);
    }

}
