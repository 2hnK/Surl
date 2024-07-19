package com.ll.demo_02.domain.article.article.repository;

import com.ll.demo_02.domain.article.article.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

// JpaRepository<Article, Long>
// Article 은 엔티티 타입을 의미한다
// Long 은 Article 엔티티의 ID 타입을 의미한다
public interface ArticleRepository extends JpaRepository<Article, Long> {

}


// 기본 CRUD 메소드 제공:
// JpaRepository 를 확장하면 기본적인 CRUD 작업을 위한 메소드가 자동으로 제공됩니다. 몇 가지 예는 다음과 같습니다:
//
// save(S entity): 엔티티를 저장하거나 업데이트합니다.
// findById(ID id): 주어진 ID로 엔티티를 찾습니다.
// findAll(): 모든 엔티티를 반환합니다.
// deleteById(ID id): 주어진 ID로 엔티티를 삭제합니다.
// delete(S entity): 주어진 엔티티를 삭제합니다.
// Page<T> findAll(Pageable pageable): 페이징 정보를 사용하여 엔티티를 찾습니다.
// List<T> findAll(Sort sort): 정렬 정보를 사용하여 모든 엔티티를 찾습니다.