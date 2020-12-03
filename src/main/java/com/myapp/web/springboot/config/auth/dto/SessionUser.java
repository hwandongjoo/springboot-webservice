package com.myapp.web.springboot.config.auth.dto;

import com.myapp.web.springboot.domain.user.User;
import lombok.Getter;

import java.io.Serializable;

// 따로 만드는 이유? 직렬화 문제때문에
//왜 기본 User 엔티티를 쓰면 안되는가? 엔티티이기 때문에 다른 것과 관계형성 가능성이 있다.
// 따라서 직렬화 기능용 세션 User를 만드는게 더 효율적이다.
@Getter
public class SessionUser implements Serializable {
    private String name;
    private String email;
    private String picture;

    public SessionUser(User user) {
        this.name = user.getName();
        this.email = user.getEmail();
        this.picture = user.getPicture();
    }
}
