package hello.hellospring;


import hello.hellospring.repository.JdbcTemplateMemberRepository;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    private DataSource dataSource;

    @Autowired
    public SpringConfig(DataSource dataSource) {
        // DI from Spring boot
        this.dataSource = dataSource;
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
        return new JdbcTemplateMemberRepository(dataSource);
    }
}
