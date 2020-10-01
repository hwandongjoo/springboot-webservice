package com.myapp.web.springboot.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;

// JpaRepository 상속시 자동으로 CRUD 메소드 생성
// 주의할점: Entity와 Entity Repository는 함께 위치!!
public interface PostsRepository extends JpaRepository<Posts,Long> {
}
