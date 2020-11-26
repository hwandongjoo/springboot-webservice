package com.myapp.web.springboot.domain.posts;

import com.myapp.web.springboot.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

// 또한 Entity에서는 Setter 사용을 지양해야함 | 생성은 왠만하면 Builder를 사용!!
@Getter             // 롬복 - getter 메소드 생성
@NoArgsConstructor  // 롬복 - 기본 생성자 자동 추가
@Entity             // JPA - 테이블과 링크될 클래스 | 기본적으로 Camel -> UnderScore로 매핑 | Ex) GoodPerson -> good_person
public class Posts extends BaseTimeEntity {
    @Id // PK 필드
    @GeneratedValue(strategy = GenerationType.IDENTITY) // PK 규칙  | GenerationType.IDENTITY => Auto Increment
    private Long id; // Long으로 할시, Mysql에서는 bigint로 변환

    @Column(length = 500, nullable = false) // 테이블 컬럼 굳이 선언 안해도 됨 | author 처럼
    private String title;                   // 주로 변경하고 싶은게 있을때 선언, length를 500으로 변경한다던지, TEXT로 타입을 정의하고 싶을때

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    @Builder // 빌더 패턴 클래스
    public Posts(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
