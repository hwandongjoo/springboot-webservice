package com.myapp.web.springboot.config.auth;

import com.myapp.web.springboot.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity // Spring Security 설정 활성화
public class SecuritConfig extends WebSecurityConfigurerAdapter {
    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .headers().frameOptions().disable() //h2-console 화면 사용을 위해서
                .and()
                    .authorizeRequests() // URL 별 권한 설정
                    .antMatchers("/","/css/**","/images/**","/js/**","/h2-console/**").permitAll()
                    .antMatchers("/api/v1/**").hasRole(Role.USER.name()) // 권한 부여 대상 설정
                    .anyRequest().authenticated() // anyMatcher => 나머지 애들 설정
                .and()
                    .logout()
                        .logoutSuccessUrl("/") //로그아웃시 / 로 이동
                .and()
                    .oauth2Login() // Oauth2 설정 진입
                        .userInfoEndpoint() // 로그인 성공 후 사용자 정보 가져올 때의 설정
                            .userService(customOAuth2UserService); // 로그인 성공 후 후속조치
    }
}
