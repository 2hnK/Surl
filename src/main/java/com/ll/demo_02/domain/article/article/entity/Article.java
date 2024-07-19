package com.ll.demo_02.domain.article.article.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity     // DB 테이블과 매핑
@Builder    // Builder 패턴을 사용하여 객체를 생성할 수 있다
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
// @Builder 만 사용하면 디폴트 생성자를 생성하지 않는다.
@AllArgsConstructor     // Builder 에서 사용됨
@NoArgsConstructor      // JPA 엔티티 인스턴스화할 때 사용됨
public class Article {
    @Id     // 해당 필드가 엔티티의 기본 키임을 나타낸다
    @GeneratedValue(strategy = IDENTITY)    // AUTO_INCREMENT
    private Long id;

    @CreatedDate
    private String title;

    @LastModifiedDate
    private LocalDateTime modifyDate;

    @Column(columnDefinition = "TEXT")  // TEXT 타입의 컬럼과 매핑됨
    private String body;
}