package com.example.wakeUp.global.config.security;

import com.example.wakeUp.global.config.redis.RedisService;
import com.example.wakeUp.global.security.auth.AuthDetails;
import com.example.wakeUp.global.security.auth.AuthDetailsService;
import com.example.wakeUp.global.security.jwt.JwtTokenProvider;
import com.example.wakeUp.global.security.jwt.JwtValidateService;
import com.example.wakeUp.global.security.jwt.filter.JwtAuthenticationFilter;
import com.example.wakeUp.global.security.jwt.filter.JwtExceptionFilter;
import io.jsonwebtoken.JwtException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtTokenProvider jwtTokenProvider;
    private final JwtValidateService jwtValidateService;
    private final AuthDetailsService authDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic().disable()
                .csrf().disable()
                .cors().disable()
                .formLogin().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                .and()
                .authorizeRequests()
                .antMatchers("/user/**").permitAll()
                .antMatchers(HttpMethod.POST, "/auth").permitAll()
                .antMatchers(HttpMethod.GET,"/auth").permitAll()
                .antMatchers(HttpMethod.POST, "/song").hasRole("USER")
                .anyRequest().authenticated()
        ;


        http
                .addFilterBefore(new JwtAuthenticationFilter(authDetailsService, jwtTokenProvider, jwtValidateService),
                        UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(new JwtExceptionFilter(),
                        JwtAuthenticationFilter.class)
        ;
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        // resources 모든 접근을 허용하는 설정을 해버리면
        // HttpSecurity 설정한 ADIM권한을 가진 사용자만 resources 접근가능한 설정을 무시해버린다.
        web.ignoring()
                .antMatchers("/resources/**");
    }
}
