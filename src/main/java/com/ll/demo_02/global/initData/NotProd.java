package com.ll.demo_02.global.initData;

import com.ll.demo_02.domain.article.article.entity.Article;
import com.ll.demo_02.domain.article.article.service.ArticleService;
import com.ll.demo_02.domain.member.member.entity.Member;
import com.ll.demo_02.domain.member.member.service.MemberService;
import com.ll.demo_02.global.exceptions.GlobalException;
import com.ll.demo_02.global.rsData.RsData;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Profile;

import java.util.List;

@Profile("!prod")   // 개발, 테스트 환경에서만 실행된다.
@Configuration      // 스프링의 설정 클래스임을 나타낸다.
@RequiredArgsConstructor    // final 필드가 포함된 생성자를 자동으로 생성
public class NotProd {
    @Lazy   // 해당 빈이 사용될 때까지 빈의 초기화를 지연시킨다.
    @Autowired  // 스프링 컨테이너가 빈을 자동으로 주입한다.
    private NotProd self;
    private final MemberService memberService;
    private final ArticleService articleService;

    @Bean   // 스프링 컨테이너에 의해 관리되는 재사용 가능한 소프트웨어 컴포넌트이다.
    public ApplicationRunner initNotProd() {    // 스프링부트 APP 시작될 때 실행되는 초기화 로직
        return args -> {
            self.work1();
            self.work2();
        };
    }

    // Transaction 으로 분리하는 이유는
    // 이후에 수행되는 로직들과 Transaction 공유로 인한 오류를 방지하기 위함이다
    @Transactional
    public void work1() {
        if (articleService.count() > 0) return;

        Member member1 = memberService.join("user1", "1234", "유저 1").getData();
        Member member2 = memberService.join("user2", "1234", "유저 2").getData();

        try {
            RsData<Member> joinRs = memberService.join("user2", "1234", "유저 2");
        } catch (GlobalException e) {
            System.out.println("e.getMsg() : " + e.getRsData().getMsg());
            System.out.println("e.getStatusCode() : " + e.getRsData().getStatusCode());
        }

        Article article1 = articleService.write("제목 1", "내용 1").getData();
        Article article2 = articleService.write("제목 2", "내용 2").getData();

        // save 되지 않은 내용은 Transaction 이 종료되면
        // JPA - Repository 비교를 통해 업데이트 한다
        article2.setTitle("제목!!");
        articleService.delete(article1);
    }

    @Transactional
    public void work2() {
        Article article = articleService.findById(2L).get();
        List<Article> articles = articleService.findAll();
    }

}
