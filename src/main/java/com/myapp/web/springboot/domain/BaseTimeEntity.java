package com.myapp.web.springboot.domain;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass //JPA Entity 클래스들이 해당 클래스를 상속할 경우 해당 클래스의 필드들도 칼럼으로 인식하게함!
@EntityListeners(AuditingEntityListener.class) // 해당 클래스에 Auditing 기능 추가
public abstract class BaseTimeEntity {
    
    @CreatedDate // Entity 생성후 저장시 시간이 자동 저장
    private LocalDateTime createdDate;
    
    @LastModifiedDate // 조회된 Entity 값을 변경할때, 시간 자동 저장
    private LocalDateTime modifiedDate;
}
