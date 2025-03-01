package hello.hello_spring;

import hello.hello_spring.repository.*;
import hello.hello_spring.service.MemberService;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;


// 컴포넌트 스캔 없이 자바 코드로 스프링 빈 등록하는 방법
// @Service, @Repository 사용 없이
// @Configuration에서 @Bean으로 각각 등록을 하면 스프링 컨테이너에 넣어줌
@Configuration
public class springConfig {

    private final MemberRepository memberRepository;

    @Autowired
    // 스프링 데이터 Jpa가 구현체를 만들어놓은 게 등록이 된다.
    public springConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
//    private EntityManager em;
//
//    @Autowired
//    public springConfig(EntityManager em) {
//        this.em = em;
//    }

    // spring이 datasource를 만들어서 관리함.
//    private DataSource dataSource;
//
//    @Autowired
//    public springConfig(DataSource dataSource) {
//        this.dataSource = dataSource;
//    }

    // return new MemberService를 하면서 하나의 객체만 등록하게 함. ( 싱글톤 패턴 )
    // memberRepository()를 매개변수로 넣으면서 의존성 주입을 함.
    @Bean
    public MemberService memberService() {
//        return new MemberService(memberRepository());
        return new MemberService(memberRepository);
    }

    // MemberService와 같이 하나의 객체만 등록하게 됨. ( 싱글톤 패턴 )
    // 이 빈은 컨트롤러에서 Autowired를 사용해서 의존성 주입을 함.
//    @Bean
//    public MemberRepository memberRepository() {
//        return new MemoryMemberRepository();
//        return new JdbcMemberRepository(dataSource);
//        return new JdbcTemplateMemberRepository(dataSource);
//        return new JpaMemberRepository(em);

//    }
}
